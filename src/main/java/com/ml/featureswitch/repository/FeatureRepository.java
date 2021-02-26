package com.ml.featureswitch.repository;

import com.ml.featureswitch.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

}

