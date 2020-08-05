package com.oocl.todos.repository;

import com.oocl.todos.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ToDoRepository extends JpaRepository<ToDo,Integer> {
}
