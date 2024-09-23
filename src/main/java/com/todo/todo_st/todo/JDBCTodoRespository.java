package com.todo.todo_st.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import ch.qos.logback.classic.Logger;

@Repository
public class JDBCTodoRespository {

  private static final Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(JDBCTodoRespository.class);

  private final JdbcClient jdbcClient;

  public JDBCTodoRespository(JdbcClient jdbcClient) {
    this.jdbcClient = jdbcClient;
  }

  // private final List<Todo> todos = new ArrayList<>();

  List<Todo> getAllTodos() {
    logger.info("Getting all todos");
    return jdbcClient.sql("SELECT * FROM todo").query(Todo.class).list();
    // return this.todos;
  }

  void addTodo(Todo todo) {
    var updated=jdbcClient.sql("INSERT INTO todo(title, description) VALUES(?,?)").params(List.of(todo.title(), todo.description())).update();
    Assert.state(updated==1, "Failed to add");
    logger.info("Todo added");
  }

  Optional<Todo> getTodoById(Integer id) {
    logger.info("Getting todo by id");
    return jdbcClient.sql("SELECT * FROM todo where id=:id").param("id", id)
        .query(Todo.class).optional();
  }

  Optional<Todo> updateTodo(Todo receivedTodo, Integer id) {
    logger.info("Updating todo");
    var updated=jdbcClient.sql("UPDATE todo SET title=?,description=?, completed=? WHERE id=?").params(
      List.of(receivedTodo.title(), receivedTodo.description(), receivedTodo.completed(), id)
    ).update();
    Assert.state(updated==1, "Failed to update");
    return getTodoById(id);
  }

  // delete
  void deleteTodo(int id) {
    logger.info("Deleting todo");
    jdbcClient.sql("DELETE FROM todo WHERE id=:id").param("id", id).query();
    logger.info("Todo deleted");
  }

  // @PostConstruct
  // @SuppressWarnings("unused")
  // private void init() {
  // todos.add(new Todo(1, "New Task", "Some Description" ));
  // todos.add(new Todo(2, "New Task 2", "Some Description 2"));
  // todos.add(new Todo(3, "New Task 3", "Some Description 3"));
  // }

}
