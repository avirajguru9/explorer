package com.ado.repo.explorer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AzureDevOpsFile {
    @JsonProperty("path")
    private String path;
    
    @JsonProperty("url")
    private String url;
    
    @JsonProperty("gitObjectType")
    private String gitObjectType;
    
    @JsonProperty("commitId")
    private String commitId;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getGitObjectType() {
        return gitObjectType;
    }

    public void setGitObjectType(String gitObjectType) {
        this.gitObjectType = gitObjectType;
    }
    
    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId;
    }
}

