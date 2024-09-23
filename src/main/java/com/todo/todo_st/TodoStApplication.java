package com.todo.todo_st;

import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TodoStApplication {

	private final static Log log = org.apache.commons.logging.LogFactory.getLog(TodoStApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoStApplication.class, args);
		var welcomeMessage = new WelcomeMessage();

		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		log.info("Application Started!! --->" + welcomeMessage.getMessage());

		// try (ConfigurableApplicationContext context =
		// SpringApplication.run(TodoStApplication.class, args)) {

		// var welcomeMessage = (WelcomeMessage) context.getBean("welcomeMessage");
		// System.out.println(welcomeMessage);

		// }

	}

	// @Bean
	// CommandLineRunner todo(){

	// 	return args->{
	// 		Todo todo = new Todo(1, "New Task", "Some Description");
	// 		log.info(todo);
	// 	};
	// }

}


