package com.ado.repo.explorer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AzureDevOpsClient {
	
	private static final String AZURE_DEVOPS_URL = "https://dev.azure.com/avirajguru9/GenAI-Repo-Explorer/_apis/git/GenAI-Repo-Explorer/{repositoryId}?api-version=6.0";
    private static final String PERSONAL_ACCESS_TOKEN = "xxx";
    private static final String ORGANIZATION = "avirajguru9";
    private static final String PROJECT = "GenAI-Repo-Explorer";
    private static final String REPOSITORY_ID = "GenAI-Repo-Explorer";
	
//	private final RestTemplate restTemplate;
	
//	@Bean
//	public AzureDevOpsClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
}
