package com.apress.todo.integration;

import com.apress.todo.config.integration.ToDoProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@EnableConfigurationProperties(ToDoProperties.class)
@Configuration
@AllArgsConstructor
public class ToDoFileIntegration {
    private final ToDoProperties props;
    private final ToDoConverter converter;

    @Bean
    public IntegrationFlow fileFlow(){
//        return IntegrationFlows
//                .from()
    }
}
