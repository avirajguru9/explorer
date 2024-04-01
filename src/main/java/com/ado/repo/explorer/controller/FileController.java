package com.ado.repo.explorer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ado.repo.explorer.service.FileReaderService;

@RestController
@RequestMapping("/azure_file")
public class FileController {

    private final FileReaderService fileReaderService;

    public FileController(FileReaderService fileReaderService) {
        this.fileReaderService = fileReaderService;
    }

    @GetMapping("/readFile/{fileId}")
    public ResponseEntity<String> readFile(@PathVariable String fileId) {
        // Construct the file URL based on the fileId
        String fileUrl = constructFileUrl(fileId);
        
        // Call the readFile method of the FileReaderService
        String fileContent = fileReaderService.readFile(fileUrl);
        
        // Return the file content in the response
        return ResponseEntity.ok(fileContent);
    }
    
    private String constructFileUrl(String fileId) {
        // Construct the file URL based on the fileId
        // You need to implement this method according to your file URL structure
    	// https://dev.azure.com/avirajguru9/GenAI-Repo-Explorer/_apis/git/repositories/afd858b9-d99b-493a-9625-1a7834595988/items/pom.xml?versionType=Branch&versionOptions=None
        return "https://dev.azure.com/avirajguru9/GenAI-Repo-Explorer/_apis/git/repositories/" + fileId + "/items//pom.xml?versionType=Branch&versionOptions=None";
    }
}

