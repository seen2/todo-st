package com.todo.todo_st.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ch.qos.logback.classic.Logger;

@Repository
public class InMemoryTodoRespository {

  private static final Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(JDBCTodoRespository.class);

  private List<Todo> todos = new ArrayList<>();

  List<Todo> getAllTodos() {
    logger.info("Getting all todos");
    return this.todos;
    // return this.todos;
  }

  void addTodo(Todo todo) {
    this.todos.add(todo);
    logger.info("Todo added");
  }

  Optional<Todo> getTodoById(Integer id) {
    logger.info("Getting todo by id");
    return this.todos.stream().filter(todo -> (int)todo.id() == (int)id).findFirst();
  }

  Todo updateTodo(Todo receivedTodo, Integer id) {
    logger.info("Updating todo");
    Todo todo=getTodoById(id).get();
    System.out.println(">>>>>>>>>>>>>>>>>>");
    System.out.println(todos.indexOf(todo));
    System.out.println(">>>>>>>>>>>>>>>>>>");
    if(todos.indexOf(todo)>=0) todos.set(todos.indexOf(todo), receivedTodo);
    todo=getTodoById(id).get();
    return todo;
  }

  // delete
  void deleteTodo(int id) {
    logger.info("Deleting todo");
    todos=todos.stream().filter(todo1 -> (int)todo1.id() != (int)id).toList();
    logger.info("Todo deleted");

    
  }

  // @PostConstruct
  // @SuppressWarnings("unused")
  // private void init() {
  //   todos.add(new Todo(1, "New Task", "Some Description", false, 0 ));
  //   todos.add(new Todo(2, "New Task 2", "Some Description 2", false, 0));
  //   todos.add(new Todo(3, "New Task 3", "Some Description 3", false, 0));
  // }

}
