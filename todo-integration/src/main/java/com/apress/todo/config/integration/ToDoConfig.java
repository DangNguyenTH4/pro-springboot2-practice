package com.apress.todo.config.integration;

import com.apress.todo.dto.ToDo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ToDoConfig {
    @Bean
    public ApplicationRunner runner(MessageChannel input){
        return args -> {
            input.send(
                    MessageBuilder.withPayload(new ToDo("Build milk today", true)).build()
            );
        };
    }
}
