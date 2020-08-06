package com.oocl.todos.service;

import com.oocl.todos.dto.ToDoRequest;
import com.oocl.todos.dto.ToDoResponse;
import com.oocl.todos.mapper.ToDoMapper;
import com.oocl.todos.model.ToDo;
import com.oocl.todos.repository.ToDoRepository;
import org.springframework.stereotype.Service;

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

    public ToDoResponse addToDo(ToDoRequest toDoRequest) {
        ToDo toDo = toDoRepository.save(ToDoMapper.convertToToDo(toDoRequest));
        return ToDoMapper.convertToToDoResponse(toDo);
    }

    public ToDoResponse modifyToDo(Integer toDoId,ToDo toDo) {
        toDo.setId(toDoId);
        return ToDoMapper.convertToToDoResponse(toDoRepository.save(toDo));
    }
    public String deleteToDoById(Integer toDoId) {
        toDoRepository.deleteById(toDoId);
        return "success";
    }
}
