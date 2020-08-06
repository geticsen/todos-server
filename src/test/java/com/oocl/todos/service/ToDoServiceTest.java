package com.oocl.todos.service;

import com.oocl.todos.dto.ToDoResponse;
import com.oocl.todos.mapper.ToDoMapper;
import com.oocl.todos.model.ToDo;
import com.oocl.todos.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ToDoServiceTest {
    @Test
    void should_return_todos_when_get_todos_given_none() {
//      given
        ToDo toDo1 = new ToDo(1,"study",false);
        ToDo toDo2 = new ToDo(2,"go home",false);
        List<ToDo> toDos =new ArrayList<>();
        toDos.add(toDo1);
        toDos.add(toDo2);
        ToDoRepository toDoRepository = mock(ToDoRepository.class);
        given(toDoRepository.findAll()).willReturn(toDos);
        ToDoService toDoService = new ToDoService(toDoRepository);
//        when
        List<ToDoResponse> getToDoResponses = toDoService.getAll();
//        then
        assertEquals(toDos.size(),getToDoResponses.size());
    }
    @Test
    void should_return_todo_when_add_todo_given_todo() {
//      given
        ToDo toDo = new ToDo(2,"go home",false);
        ToDoRepository toDoRepository = mock(ToDoRepository.class);
        given(toDoRepository.save(toDo)).willReturn(toDo);
        ToDoService toDoService = new ToDoService(toDoRepository);
//        when
        ToDoResponse toDoResponse = toDoService.addToDo(toDo);
//        then
        assertEquals(toDo.getId(),toDoResponse.getId());
        assertEquals(toDo.getContent(),toDoResponse.getContent());
        assertEquals(toDo.getStatus(),toDoResponse.getStatus());
    }

    @Test
    void should_return_modify_todo_when_modify_todo_given_new_todo() {
//      given
        ToDo toDo = new ToDo(2,"go home",false);
        ToDoRepository toDoRepository = mock(ToDoRepository.class);
        given(toDoRepository.save(toDo)).willReturn(toDo);
        ToDoService toDoService = new ToDoService(toDoRepository);
//        when
        ToDoResponse toDoResponse = toDoService.modifyToDo(toDo.getId(),toDo);
//        then
        assertEquals(toDo.getId(),toDoResponse.getId());
        assertEquals(toDo.getContent(),toDoResponse.getContent());
        assertEquals(toDo.getStatus(),toDoResponse.getStatus());
    }
    @Test
    void should_return_message_when_delete_todo_given_todo_id() {
//      given
        String message = "success";
        ToDo toDo = new ToDo(2,"go home",false);
        ToDoRepository toDoRepository = mock(ToDoRepository.class);
        ToDoService toDoService = new ToDoService(toDoRepository);
//        when
        String getmessage = toDoService.deleteToDoById(toDo.getId());
//        then
        assertEquals(message,getmessage);
    }

}
