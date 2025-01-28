package com.mycompany.todo_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mycompany.todo_service.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
