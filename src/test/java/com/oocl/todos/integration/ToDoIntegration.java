package com.oocl.todos.integration;

import com.oocl.todos.model.ToDo;
import com.oocl.todos.repository.ToDoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        List<ToDo> toDos =new ArrayList<>();
        toDos.add(new ToDo(1,"hello world",false));
        toDos.add(new ToDo(2,"hello green",false));
        toDoRepository.saveAll(toDos);
//        when then
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[1].id").isNumber());
    }
}
