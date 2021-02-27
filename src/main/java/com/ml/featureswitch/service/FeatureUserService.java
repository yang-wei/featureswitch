package com.ml.featureswitch.service;

import com.ml.featureswitch.model.Feature;
import com.ml.featureswitch.model.User;
import com.ml.featureswitch.repository.FeatureRepository;
import com.ml.featureswitch.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

@Service
public class FeatureUserService {

    private final FeatureRepository featureRepository;
    private final UserRepository userRepository;

    public FeatureUserService(FeatureRepository featureRepository, UserRepository userRepository) {
        this.featureRepository = featureRepository;
        this.userRepository = userRepository;
    }

    public Feature findFeatureByName(String featureName) {
        return featureRepository.findByName(featureName);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isEnable(Feature feature, User user) {
        return feature.hasUser(user);
    }

    public boolean toggle(Feature feature, User user, boolean enable) {
        if (feature.hasUser(user) && enable) {
            return false;
        }
        if (!feature.hasUser(user) && !enable) {
            return false;
        }
        if (enable) {
            feature.addUser(user);
        } else {
            feature.removeUser(user);
        }
        featureRepository.save(feature);
        return true;
    }
}
