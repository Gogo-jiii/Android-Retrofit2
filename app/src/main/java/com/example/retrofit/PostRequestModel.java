package com.example.retrofit;

public class PostRequestModel {

    private String name;

    public PostRequestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public String toString() {
        return "PostRequestModel{" +
                "name='" + name + '\'' +
                '}';
    }
}
