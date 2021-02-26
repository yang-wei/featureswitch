package com.ml.featureswitch.controller;

import java.util.List;

import com.ml.featureswitch.model.Feature;
import com.ml.featureswitch.repository.FeatureRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class FeatureController {
    private final FeatureRepository repository;

    FeatureController(FeatureRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/features")
    List<Feature> all() {
        return repository.findAll();
    }

}
