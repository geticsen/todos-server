package com.oocl.todos.controller;

import com.oocl.todos.dto.ToDoRequest;
import com.oocl.todos.dto.ToDoResponse;
import com.oocl.todos.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class ToDosController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ToDoResponse> getAll() {
        return toDoService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoResponse addToDo(@RequestBody ToDoRequest toDoRequest){
           return toDoService.addToDo(toDoRequest);
    }
}
