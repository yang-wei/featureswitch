package com.ml.featureswitch.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ml.featureswitch.model.Feature;
import com.ml.featureswitch.model.User;
import com.ml.featureswitch.repository.FeatureRepository;
import com.ml.featureswitch.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FeatureUserController {
    private final FeatureRepository featureRepository;
    private final UserRepository userRepository;

    FeatureUserController(FeatureRepository featureRepository, UserRepository userRepository) {
        this.featureRepository = featureRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/feature")
    public ResponseEntity<String> get(@RequestParam String email, @RequestParam String featureName) {
        Feature feature = featureRepository.findByName(featureName);
        if (feature == null) {
            String response = String.format("{\"message\":\"%s is not a feature\"}", featureName);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findByEmail(email);
        if (user == null) {
            String response = String.format("{\"message\":\"%s does not exist\"}", email);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        String response = String.format("{\"canAccess\":%b}", feature.hasUser(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
