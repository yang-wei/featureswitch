package com.ml.featureswitch;


import com.ml.featureswitch.model.User;
import com.ml.featureswitch.model.Feature;
import com.ml.featureswitch.repository.FeatureRepository;
import com.ml.featureswitch.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initFeatureDatabase(FeatureRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Feature("autoinvest")));
            log.info("Preloading " + repository.save(new Feature("instacash")));
        };
    }

    @Bean
    CommandLineRunner initUserDatabase(UserRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new User("user1@gmail.com")));
            log.info("Preloading " + repository.save(new User("user2@gmail.com")));
        };
    }
}
