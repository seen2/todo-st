package com.todo.todo_st.user;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserRestClient {

  private RestClient restClient;

  public UserRestClient(RestClient.Builder builder) {
    restClient = builder.baseUrl("https://jsonplaceholder.typicode.com").build();
  }

  public List<User> getAllUsers() {
    return restClient
    .get()
    .uri("/users")
    .retrieve()
    .body(new ParameterizedTypeReference<>() {
    });
  }

  public User getUserById(int id) {
    return restClient
    .get()
    .uri("/users/{id}", id)
    .retrieve()
    .body(User.class);
  }

}
