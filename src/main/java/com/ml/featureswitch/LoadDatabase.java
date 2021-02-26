package com.ml.featureswitch;


import com.ml.featureswitch.model.Feature;
import com.ml.featureswitch.repository.FeatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(FeatureRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Feature("autoinvest")));
            log.info("Preloading " + repository.save(new Feature("instacash")));
        };
    }
}
