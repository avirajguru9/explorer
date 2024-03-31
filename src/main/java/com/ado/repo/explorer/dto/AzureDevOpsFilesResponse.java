package com.ado.repo.explorer.dto;

import java.util.List;

import com.ado.repo.explorer.model.AzureDevOpsFile;

public class AzureDevOpsFilesResponse {
    private int count;
    private List<AzureDevOpsFile> value;

    // Getters and setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AzureDevOpsFile> getValue() {
        return value;
    }

    public void setValue(List<AzureDevOpsFile> value) {
        this.value = value;
    }
}
