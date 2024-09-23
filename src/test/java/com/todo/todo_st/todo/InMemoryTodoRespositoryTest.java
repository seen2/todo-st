package com.todo.todo_st.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InMemoryTodoRespositoryTest {

  InMemoryTodoRespository repository;

  @BeforeEach
  void setUp() {
    repository = new InMemoryTodoRespository();
    repository.addTodo(new Todo(1, "New Task", "Some Description", false, 0));
    repository.addTodo(new Todo(2, "New Task 2", "Some Description 2", false, 0));
    repository.addTodo(new Todo(3, "New Task 3", "Some Description 3", false, 0));

  }

  @Test
  void testAddTodo() {
    var todo = new Todo(4, "New Task 4", "Some Description 4", false, 0);
    repository.addTodo(todo);
    var todos = repository.getAllTodos();
    assertEquals(todos.size(), 4, "Size should be 4");

  }

  @Test
  void testDeleteTodo() {
    repository.deleteTodo(1);
    assertTrue(repository.getTodoById(1).isEmpty(), "Should be Empty");

  }

  @Test
  void testGetAllTodos() {
    var todos = repository.getAllTodos();
    // assert todos.size() == 3;
    assertEquals(todos.size(), 3, "Size should be 3");

  }

  @Test
  void testGetTodoById() {
    var todo = repository.getTodoById(1);
    assertEquals(todo.get().title(), "New Task", "Title should be New Task");
  }

  @Test
  void testUpdateTodo() {
    var todo = new Todo(1, "New Updated Task", "Some Description", false, 0);
    var updatedTodo = repository.updateTodo(todo, 1);
    assertEquals(updatedTodo.title(), "New Updated Task", "Title should be New Updated Task");
  }

}
