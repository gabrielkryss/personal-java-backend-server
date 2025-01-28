package com.mycompany.todo_service.controller;

import com.mycompany.todo_service.entity.Todo;
import com.mycompany.todo_service.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
  @Autowired
  private TodoService todoService;

  @GetMapping
  public List<Todo> getAllTodos() {
    return todoService.getAllTodos();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
    Todo todo = todoService.getTodoById(id);
    return ResponseEntity.ok(todo);
  }

  @PostMapping
  public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
    Todo createdTodo = todoService.createTodo(todo);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
    Todo updatedTodo = todoService.updateTodo(id, todoDetails);
    return ResponseEntity.ok(updatedTodo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
    todoService.deleteTodo(id);
    return ResponseEntity.noContent().build();
  }
}
