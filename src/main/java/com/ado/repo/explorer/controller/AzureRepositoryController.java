package com.ado.repo.explorer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ado.repo.explorer.service.AzureDevOpsRepositoryService;

@RestController
@RequestMapping("/azure_repo")
public class AzureRepositoryController {
	
	@Autowired
	AzureDevOpsRepositoryService repositoryService;
	
	@GetMapping("get_list_repo")
	public String getListOfRepo() {
		return repositoryService.listRepositories();
	}
}
