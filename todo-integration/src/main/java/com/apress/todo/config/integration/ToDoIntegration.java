package com.apress.todo.config.integration;

import com.apress.todo.dto.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;

@EnableIntegration
@Configuration
public class ToDoIntegration {
    @Bean
    public DirectChannel input(){
        return MessageChannels.direct().get();
    }
    @Bean
    public IntegrationFlow simpleFlow(){
        return IntegrationFlows.from(input())
                .filter(ToDo.class,ToDo::getCompleted)
                .transform(ToDo.class,
                        toDo -> toDo.getDescription().toUpperCase())
                .handle(System.out::println).get();
    }
    @Bean
    public MessageChannel toLog(){
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "toLog")
    public LoggingHandler logging() {
        LoggingHandler adapter = new
                LoggingHandler(LoggingHandler.Level.INFO);
        adapter.setLoggerName("SIMPLE_LOGGER");
        adapter.setLogExpressionString
                ("headers.id + ': ' + payload");
        return adapter;
    }
    @MessageEndpoint
    class SimpleFilter{
        @Filter(inputChannel = "input", outputChannel = "toTransform")
        public boolean process(ToDo message){
            return message.getCompleted();
        }
    }
}

@MessageEndpoint
class SimpleTransformer{
    @Transformer(inputChannel = "toTransform", outputChannel = "toLog")
    public String process(ToDo message){
        return message.getDescription().toUpperCase();
    }
}
@MessageEndpoint
@Slf4j
class SimpleServiceActivator{
    @ServiceActivator(inputChannel = "toLog")
    public void process(String message){
        log.info(message);
    }
}