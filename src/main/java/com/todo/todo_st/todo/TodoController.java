package com.todo.todo_st.todo;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoRepository todoRespository;
  private final Logger logger = (Logger) LoggerFactory.getLogger(TodoController.class);

  public TodoController(TodoRepository todoRespository) {
    this.todoRespository = todoRespository;
  }

  // @GetMapping("/")
  // public String home() {
  // return "Hello World!";
  // }

  @GetMapping("/")
  public List<Todo> getAllTodos() {
    return todoRespository.findAll();
  }

  @GetMapping("/{id}")
  public Todo getTodoById(@PathVariable int id) {
    Optional<Todo> todo = todoRespository.findById(id);
    if (todo.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {

      return todo.get();
    }
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public String createTodo(@Valid @RequestBody Todo todo) {
    todoRespository.save(todo);
    return "Todo added";
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<Todo> updateTodo(@PathVariable Integer id, @Valid @RequestBody Todo newTodo) {
    todoRespository.save(newTodo);
    return todoRespository.findById(id);
  }

  // delete
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTodo(@PathVariable int id) {
    todoRespository.deleteById(id);
  }

  @GetMapping("/search")
  public List<Todo> getMethodName(@RequestParam(required = false) String title) {
    logger.info("Getting todos by title");
    logger.info("Title: {}", title);
    if (title == null) {
      return todoRespository.findAll();
    } else {

      return todoRespository.findAllByTitle(title);
    }
  }

}
