package com.apress.todo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ToDo {
    private String id;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean  completed;
    public ToDo(){
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }
    public ToDo(String description){
        this();
        this.description = description;
    }
    public ToDo(String description,boolean completed){
        this(description);
        this.completed = completed;
    }
}
