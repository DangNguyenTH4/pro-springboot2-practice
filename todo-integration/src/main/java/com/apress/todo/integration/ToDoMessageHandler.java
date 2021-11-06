package com.apress.todo.integration;

import com.apress.todo.dto.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ToDoMessageHandler {
    public void process(ToDo todo){
        log.info(">>> {}", todo);

    }
}
