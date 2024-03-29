package com.ado.repo.explorer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ado.repo.explorer.configuration.AzureDevOpsProperties;

import java.util.Base64;

@Service
public class AzureDevOpsRepositoryService {

    @Autowired
    private AzureDevOpsProperties azureProperties;

    private static final String REPOSITORIES_ENDPOINT = "_apis/git/repositories";

    private final RestTemplate restTemplate;

    public AzureDevOpsRepositoryService() {
        this.restTemplate = new RestTemplate();
    }

    public String listRepositories() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + base64Encode(":" + azureProperties.getPat()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String fullUrl = azureProperties.getOrganizationUrl() + azureProperties.getProjectName() + "/" + REPOSITORIES_ENDPOINT;
        ResponseEntity<String> response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity, String.class);
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

