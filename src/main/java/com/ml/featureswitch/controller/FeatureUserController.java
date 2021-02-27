package com.ml.featureswitch.controller;

import com.ml.featureswitch.controller.http.GetFeatureUserRequest;
import com.ml.featureswitch.controller.http.NewFeatureUserRequest;
import com.ml.featureswitch.model.Feature;
import com.ml.featureswitch.model.User;
import com.ml.featureswitch.service.FeatureUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FeatureUserController {
    private final FeatureUserService service;

    FeatureUserController(FeatureUserService service) {
        this.service = service;
    }

    @GetMapping("/feature")
    public ResponseEntity<String> get(GetFeatureUserRequest request) {
        Feature feature = service.findFeatureByName(request.getFeatureName());
        if (feature == null) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        User user = service.findUserByEmail(request.getEmail());
        if (user == null) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        String response = String.format("{\"canAccess\":%b}", service.isEnable(feature, user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/feature")
    public ResponseEntity<String> newFeatureUser(@RequestBody NewFeatureUserRequest request) {
        Feature feature = service.findFeatureByName(request.getFeatureName());
        if (feature == null) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        User user = service.findUserByEmail(request.getEmail());
        if (user == null) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        boolean toggled = service.toggle(feature, user, request.isEnable());
        if (toggled) {
            return new ResponseEntity<>("", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("", HttpStatus.NOT_MODIFIED);
        }
    }

}
