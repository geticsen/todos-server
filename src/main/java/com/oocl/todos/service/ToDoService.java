package com.oocl.todos.service;

import com.oocl.todos.dto.ToDoResponse;
import com.oocl.todos.mapper.ToDoMapper;
import com.oocl.todos.model.ToDo;
import com.oocl.todos.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDoResponse> getAll() {
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos.stream().map(ToDoMapper::convertToToDoResponse).collect(Collectors.toList());

    }

    public ToDoResponse addToDo(ToDo toDo) {
        return ToDoMapper.convertToToDoResponse(toDoRepository.save(toDo));
    }
}
