package com.oocl.todos.integration;

import com.oocl.todos.model.ToDo;
import com.oocl.todos.repository.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoIntegration {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ToDoRepository toDoRepository;

    @AfterEach
    void deleteData() {
        toDoRepository.deleteAll();
        toDoRepository.deleteAll();
    }

    @Test
    void shuld_return_todos_when_get_all_todo_given_none() throws Exception {
//        given
        List<ToDo> toDos = new ArrayList<>();
        toDos.add(new ToDo(1, "hello world", false));
        toDos.add(new ToDo(2, "hello green", false));
        toDoRepository.saveAll(toDos);
//        when then
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[1].id").isNumber());
    }

    @Test
    void should_return_todos_when_add_todo_given_todo() throws Exception {
//        given
        String toDoString = "{\n" +
                "    \"id\": 0,\n" +
                "    \"content\": \"123456\",\n" +
                "    \"status\": false\n" +
                "}";
//        when then
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON).content(toDoString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.content").value("123456"))
                .andExpect(jsonPath("$.status").value(false));
    }

    @Test
    void should_return_modify_todo_when_modify_todo_given_modify_todo() throws Exception {
//        given
        ToDo toDo = new ToDo(1, "do home work", false);
        ToDo savedTdo = toDoRepository.save(toDo);
        String toDoString = "{\n" +
                "    \"id\": " + savedTdo.getId() + ",\n" +
                "    \"content\": \"123456\",\n" +
                "    \"status\": true\n" +
                "}";
//        when then
        mockMvc.perform(put("/todos/" + savedTdo.getId())
                .contentType(MediaType.APPLICATION_JSON).content(toDoString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedTdo.getId()))
                .andExpect(jsonPath("$.content").value("123456"))
                .andExpect(jsonPath("$.status").value(true));
    }

    @Test
    void should_return_message_when_delete_todo_given_todo_id() throws Exception {
//        given
        ToDo savedToDo =  toDoRepository.save(new ToDo(1, "123456", false));
        Integer toDoId =savedToDo.getId();
//        when then
        mockMvc.perform(delete("/todos/" + toDoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("success"));

    }
}
