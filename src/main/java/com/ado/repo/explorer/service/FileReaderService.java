package com.ado.repo.explorer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ado.repo.explorer.configuration.AzureDevOpsProperties;

@Service
public class FileReaderService {

	@Autowired
    private AzureDevOpsProperties azureProperties;
	
	private final RestTemplate restTemplate;

    public FileReaderService() {
        this.restTemplate = new RestTemplate();
    }

    public String readFile(String fileUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(azureProperties.getPat()); // Set PAT token in the Authorization header

        ResponseEntity<String> response = restTemplate.exchange(fileUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to read file from URL: " + fileUrl);
        }
    }
}

