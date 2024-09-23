package com.todo.todo_st;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

  @GetMapping("/")
  public String welcome() {
      return new WelcomeMessage().getMessage();
  }
  

}
