package com.durarweb.newsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeadlinesResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("sources")
    private List<SourceHeadLines> sourceHeadLines;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SourceHeadLines> getSources() {
        return sourceHeadLines;
    }

    public void setSources(List<SourceHeadLines> sourceHeadLines) {
        this.sourceHeadLines = sourceHeadLines;
    }

}