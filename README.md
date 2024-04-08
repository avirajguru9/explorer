Title: Utilizing Azure DevOps APIs in Spring Boot Application

Fetch repositories and source code from Azure DevOps https://aex.dev.azure.com/ 

1) Fetch the list of repositories
2) Given a Repository Fetch the list of branches
3) Given a Repository/branch fetch the  list of source files.
4) Fetch the Content of any one source files

Introduction:
In modern software development workflows, seamless integration with version control systems like Azure DevOps is crucial. This article demonstrates how to interact with Azure DevOps APIs in a Spring Boot application to list repositories and files.

Code Overview:
The AzureDevOpsRepositoryService class encapsulates the functionality to interact with Azure DevOps APIs. It leverages the RestTemplate for making HTTP requests and the ObjectMapper for JSON serialization/deserialization.

List Repositories:
The listRepositories method retrieves a list of repositories from Azure DevOps. It constructs the API URL using organization URL, project name, and the repositories endpoint. It sets the appropriate HTTP headers, including the Personal Access Token (PAT) for authentication. The method then performs an HTTP GET request to the Azure DevOps API and returns the response body if successful.

List Files:
The listFiles method retrieves a list of files from a specific repository in Azure DevOps. Similar to listing repositories, it constructs the API URL using organization URL, project name, repository ID, and the files endpoint. It sets the necessary HTTP headers and performs an HTTP GET request. Upon receiving the response, it deserializes the JSON response into a list of AzureDevOpsFile objects using the ObjectMapper.

Base64 Encoding:
The base64Encode method encodes the provided string using Base64 encoding. It is used to encode the Personal Access Token before setting it in the HTTP headers.

Usage:
To utilize the AzureDevOpsRepositoryService, inject it into your Spring components and invoke its methods. Ensure that the AzureDevOpsProperties bean is configured with the required Azure DevOps organization URL, project name, and PAT.

Conclusion:
Integrating with Azure DevOps APIs allows for seamless automation and interaction with version control repositories. By leveraging Spring Boot's capabilities, developers can build robust applications that interact with Azure DevOps services effectively, enhancing their development workflows.
