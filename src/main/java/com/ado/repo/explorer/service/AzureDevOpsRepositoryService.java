package com.ado.repo.explorer.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@Service
public class AzureDevOpsRepositoryService {
	
    private static final String ORGANIZATION_URL = "https://dev.azure.com/avirajguru9/";
    private static final String PROJECT_NAME = "GenAI-Repo-Explorer";
    private static final String PAT = "xx";

    private static final String REPOSITORIES_ENDPOINT = ORGANIZATION_URL + PROJECT_NAME + "/_apis/git/repositories";

    private final RestTemplate restTemplate;

    public AzureDevOpsRepositoryService() {
        this.restTemplate = new RestTemplate();
    }

    public String listRepositories() {
    	
    	HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + base64Encode(":" + PAT));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(REPOSITORIES_ENDPOINT, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return "Failed to retrieve repositories: " + response.getStatusCode();
        }
        
    }
    
    private static String base64Encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }

}
