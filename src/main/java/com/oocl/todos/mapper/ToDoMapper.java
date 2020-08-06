package com.oocl.todos.mapper;

import com.oocl.todos.dto.ToDoRequest;
import com.oocl.todos.dto.ToDoResponse;
import com.oocl.todos.model.ToDo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ToDoMapper {
    public static ToDo convertToToDo(ToDoRequest toDoRequest){
        ToDo toDo = new ToDo();
        BeanUtils.copyProperties(toDoRequest,toDo);
        return toDo;
    }
    public static ToDoResponse convertToToDoResponse(ToDo toDo){
        ToDoResponse toDoResponse = new ToDoResponse();
        BeanUtils.copyProperties(toDo,toDoResponse);
        return toDoResponse;
    }
}
