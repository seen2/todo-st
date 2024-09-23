package com.todo.todo_st.user;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserHttpClient {

  @GetExchange("/users")
  List<User> getAllUsers();

  @GetExchange("/users/{id}")
  User getUserById(@PathVariable int id);

}
