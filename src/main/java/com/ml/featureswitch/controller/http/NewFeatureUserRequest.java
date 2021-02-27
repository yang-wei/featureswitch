package com.ml.featureswitch.controller.http;

public class NewFeatureUserRequest {
    private final String featureName;
    private final String email;
    private final boolean enable;

    public NewFeatureUserRequest(String featureName, String email, boolean enable) {
        this.featureName = featureName;
        this.email = email;
        this.enable = enable;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnable() {
        return enable;
    }
}
