package com.oocl.todos.service;

import com.oocl.todos.dto.ToDoRequest;
import com.oocl.todos.dto.ToDoResponse;
import com.oocl.todos.exception.NoSuchDataException;
import com.oocl.todos.mapper.ToDoMapper;
import com.oocl.todos.model.ToDo;
import com.oocl.todos.repository.ToDoRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ToDoServiceTest {
    @Test
    void should_return_todos_when_get_todos_given_none() {
//      given
        ToDo toDo1 = new ToDo(1, "study", false);
        ToDo toDo2 = new ToDo(2, "go home", false);
        List<ToDo> toDos = new ArrayList<>();
        toDos.add(toDo1);
        toDos.add(toDo2);
        ToDoRepository toDoRepository = mock(ToDoRepository.class);
        given(toDoRepository.findAll()).willReturn(toDos);
        ToDoService toDoService = new ToDoService(toDoRepository);
//        when
        List<ToDoResponse> getToDoResponses = toDoService.getAll();
//        then
        assertEquals(toDos.size(), getToDoResponses.size());
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() {
//      given
        ToDoRequest toDoRequest = new ToDoRequest(2, "go home", false);
        ToDo toDo = ToDoMapper.convertToToDo(toDoRequest);
        ToDoRepository toDoRepository = mock(ToDoRepository.class);
        given(toDoRepository.save(toDo)).willReturn(toDo);
        ToDoService toDoService = new ToDoService(toDoRepository);
//        when

        ToDoResponse toDoResponse = toDoService.addToDo(toDoRequest);
//        then
        assertEquals(toDo.getId(), toDoResponse.getId());
        assertEquals(toDo.getContent(), toDoResponse.getContent());
        assertEquals(toDo.getStatus(), toDoResponse.getStatus());
    }

    @Test
    void should_return_modify_todo_when_modify_todo_given_new_todo() throws NoSuchDataException {
//      given
        ToDo toDo = new ToDo(2, "go home", false);
        ToDoRequest toDoRequest = new ToDoRequest(2, "go home", false);
        ToDoRepository toDoRepository = mock(ToDoRepository.class);
        given(toDoRepository.save(toDo)).willReturn(toDo);
        ToDoService toDoService = new ToDoService(toDoRepository);
//        when
        ToDoResponse toDoResponse = toDoService.modifyToDo(toDo.getId(), toDoRequest);
//        then
        assertEquals(toDo.getId(), toDoResponse.getId());
        assertEquals(toDo.getContent(), toDoResponse.getContent());
        assertEquals(toDo.getStatus(), toDoResponse.getStatus());
    }

    @Test
    void should_return_message_when_delete_todo_given_todo_id() {
//      given
        String message = "success";
        ToDo toDo = new ToDo(2, "go home", false);
        ToDoRepository toDoRepository = mock(ToDoRepository.class);
        ToDoService toDoService = new ToDoService(toDoRepository);
//        when
        String getmessage = toDoService.deleteToDoById(toDo.getId());
//        then
        assertEquals(message, getmessage);
    }
    @Test
    void should_throw_no_such_data_exception_when_update_employee_given_wrong_id() {
//        given
        int toDoId = -1;
        ToDoRepository toDoRepository = mock(ToDoRepository.class);

        ToDoService toDoService = new ToDoService(toDoRepository);
//        when
        Exception exception = assertThrows(NoSuchDataException.class, () -> toDoService.modifyToDo(toDoId, new ToDoRequest()));

//        then
        assertEquals(NoSuchDataException.class, exception.getClass());
    }


}
