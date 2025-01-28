package com.mycompany.todo_service.service;

import com.mycompany.todo_service.entity.Todo;
import com.mycompany.todo_service.exception.ResourceNotFoundException;
import com.mycompany.todo_service.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
  }

  public Todo getTodoById(Long id) {
    return todoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
  }

  public Todo createTodo(Todo todo) {
    return todoRepository.save(todo);
  }

  public Todo updateTodo(Long id, Todo todoDetails) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
    todo.setTitle(todoDetails.getTitle());
    todo.setDescription(todoDetails.getDescription());
    todo.setCompleted(todoDetails.isCompleted());
    return todoRepository.save(todo);
  }

  public void deleteTodo(Long id) {
    Todo todo = todoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
    todoRepository.delete(todo);
  }
}
