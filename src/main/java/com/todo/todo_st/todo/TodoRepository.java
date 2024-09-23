package com.todo.todo_st.todo;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;


public interface TodoRepository extends ListCrudRepository<Todo, Integer> {

  // @Query("SELECT * FROM todo WHERE title LIKE %?1%")
  List<Todo> findAllByTitle(String title);

}
