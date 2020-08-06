package com.oocl.todos.service;

import com.oocl.todos.dto.ToDoRequest;
import com.oocl.todos.dto.ToDoResponse;
import com.oocl.todos.exception.IllegalOperationException;
import com.oocl.todos.exception.NoSuchDataException;
import com.oocl.todos.mapper.ToDoMapper;
import com.oocl.todos.model.ToDo;
import com.oocl.todos.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDoResponse> getAll() {
        List<ToDo> toDos = toDoRepository.findAll();
        return toDos.stream().map(ToDoMapper::convertToToDoResponse).collect(Collectors.toList());

    }

    public ToDoResponse addToDo(ToDoRequest toDoRequest) throws IllegalOperationException {
        if (toDoRequest.getContent() == null){
            throw new IllegalOperationException();
        }
        ToDo toDo = toDoRepository.save(ToDoMapper.convertToToDo(toDoRequest));
        return ToDoMapper.convertToToDoResponse(toDo);
    }

    public ToDoResponse modifyToDo(Integer toDoId,ToDoRequest toDoRequest) throws NoSuchDataException {
        Optional optional =toDoRepository.findById(toDoId);
        if(optional.isPresent()){
            toDoRequest.setId(toDoId);
            ToDo toDo = ToDoMapper.convertToToDo(toDoRequest);
            return ToDoMapper.convertToToDoResponse(toDoRepository.save(toDo));
        }else {
            throw new NoSuchDataException();
        }

    }
    public String deleteToDoById(Integer toDoId) {
        toDoRepository.deleteById(toDoId);
        return "success";
    }
}
