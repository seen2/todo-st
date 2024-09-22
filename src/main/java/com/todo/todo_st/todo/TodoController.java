package com.todo.todo_st.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoRespository todoRespository;

  public TodoController(TodoRespository todoRespository) {
    this.todoRespository = todoRespository;
  }

  // @GetMapping("/")
  // public String home() {
  // return "Hello World!";
  // }

  @GetMapping("/")
  public List<Todo> getAllTodos() {
    return todoRespository.getAllTodos();
  }

  @GetMapping("/{id}")
  public Todo getTodoById(@PathVariable int id) {
    Optional<Todo> todo = todoRespository.getTodoById(id);
    if (todo.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {

      return todo.get();
    }
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public String createTodo( @Valid @RequestBody Todo todo) {
      todoRespository.addTodo(todo);
      return "Todo added";
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Todo> updateTodo(@PathVariable Integer id, @Valid @RequestBody Todo newTodo) {
      Optional<Todo> todo = todoRespository.updateTodo(newTodo, id);      
      return todo;
  }

  //delete
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTodo(@PathVariable int id) {
    todoRespository.deleteTodo(id);
  }
  

}
