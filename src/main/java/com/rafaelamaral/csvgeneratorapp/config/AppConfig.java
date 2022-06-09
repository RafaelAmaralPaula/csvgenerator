package com.rafaelamaral.csvgeneratorapp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AppConfig {

    @Data
    @Component
    public class Property {

        @Value("${queries-path-name:queries}")
        private String queriesPathName;

        @Value("${results-path-name:results}")
        private String resultsPathName;
    }

}
