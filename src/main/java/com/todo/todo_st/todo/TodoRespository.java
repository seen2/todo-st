package com.todo.todo_st.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import jakarta.annotation.PostConstruct;

@Repository
public class TodoRespository {

  private final List<Todo> todos = new ArrayList<>();

  List<Todo> getAllTodos() {
    return todos;
  }

  void addTodo(Todo todo) {
    todos.add(todo);
  }

  Optional<Todo> getTodoById(int id) {
    return todos.stream().filter(todo -> todo.id() == id).findFirst();
  }

  Optional<Todo> updateTodo(Todo receivedTodo, Integer id) {
    Optional<Todo> foundTodo = this.getTodoById(id);
    if (foundTodo.isPresent()) {
      todos.set(todos.indexOf(foundTodo.get()), receivedTodo);
      return Optional.of(receivedTodo);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

  }

  //delete
  void deleteTodo(int id) {
    todos.removeIf(todo -> todo.id() == id);
  }


  @PostConstruct
  @SuppressWarnings("unused")
  private void init() {
    todos.add(new Todo(1, "New Task", "Some Description" ));
    todos.add(new Todo(2, "New Task 2", "Some Description 2"));
    todos.add(new Todo(3, "New Task 3", "Some Description 3"));
  }

}
