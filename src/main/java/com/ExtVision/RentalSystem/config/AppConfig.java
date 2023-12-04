/*package com.ExtVision.RentalSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ExtVision.RentalSystem.DVD.StateFactory;
import com.ExtVision.RentalSystem.DVD.StateFactoryImpl;

// AppConfig class is used for Spring's Java-based configuration.
// This class defines beans and their configurations for the Spring application context.
@Configuration
public class AppConfig {

    // @Bean annotation tells Spring that a method produces a bean to be managed by the Spring container.
    // In this case, the method stateFactory() is declared as a bean producer.
    @Bean
    public StateFactory stateFactory() {
        // The method returns an instance of StateFactoryImpl.
        // This instance will be managed by Spring and can be injected wherever a StateFactory is required.
        return new StateFactoryImpl();
    }
}*/
