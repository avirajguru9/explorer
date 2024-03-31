package com.ado.repo.explorer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ado.repo.explorer.configuration.AzureDevOpsProperties;
import com.ado.repo.explorer.dto.AzureDevOpsFilesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import java.util.Base64;
import com.ado.repo.explorer.model.AzureDevOpsFile;

@Service
public class AzureDevOpsRepositoryService {

    @Autowired
    private AzureDevOpsProperties azureProperties;

    private static final String REPOSITORIES_ENDPOINT = "_apis/git/repositories";

    private static final String FILES_ENDPOINT = "_apis/git/repositories/{repositoryId}/items";
    private static final String REPOSITORY_ID = "GenAI-Repo-Explorer";
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AzureDevOpsRepositoryService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
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

//    public String listFiles() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Basic " + base64Encode(":" + azureProperties.getPat()));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        String url = azureProperties.getOrganizationUrl() + azureProperties.getProjectName() + "/" +
//                     FILES_ENDPOINT.replace("{repositoryId}", REPOSITORY_ID) +
//                     "?scopePath=/" + "&recursionLevel=OneLevel";
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            return response.getBody();
//        } else {
//            return "Failed to retrieve files: " + response.getStatusCode();
//        }
//    }
    
    public List<AzureDevOpsFile> listFiles() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + base64Encode(":" + azureProperties.getPat()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = azureProperties.getOrganizationUrl() + azureProperties.getProjectName() + "/" +
                     FILES_ENDPOINT.replace("{repositoryId}", REPOSITORY_ID) +
                     "?scopePath=/" + "&recursionLevel=OneLevel";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                String responseBody = response.getBody();
                AzureDevOpsFilesResponse filesResponse = objectMapper.readValue(responseBody, AzureDevOpsFilesResponse.class);
                return filesResponse.getValue();
            } catch (Exception e) {
                e.printStackTrace(); // Handle exception properly
            }
        }
        return null; // Return null if failed to retrieve or parse files
    }
    
    private static String base64Encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }
}

