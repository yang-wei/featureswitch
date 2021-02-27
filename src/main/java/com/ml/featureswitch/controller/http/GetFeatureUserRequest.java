package com.ml.featureswitch.controller.http;

public class GetFeatureUserRequest {
    private final String featureName;
    private final String email;

    public GetFeatureUserRequest(String featureName, String email) {
        this.featureName = featureName;
        this.email = email;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getEmail() {
        return email;
    }
}

