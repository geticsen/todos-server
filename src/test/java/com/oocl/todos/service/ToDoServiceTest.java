package com.oocl.todos.service;

import com.oocl.todos.dto.ToDoResponse;
import com.oocl.todos.model.ToDo;
import com.oocl.todos.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


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
        List<ToDoResponse> getToDos = toDoService.getAll();
//        then
        assertEquals(toDos.size(),getToDos.size());
    }
}
