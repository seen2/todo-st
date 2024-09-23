package com.todo.todo_st;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.todo.todo_st.user.User;
import com.todo.todo_st.user.UserHttpClient;
import com.todo.todo_st.user.UserRestClient;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class TodoStApplication {

	private final static Logger logger = (Logger) org.slf4j.LoggerFactory.getLogger(TodoStApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoStApplication.class, args);

	}

	@Bean
	UserHttpClient userHttpClient() {
		RestClient client = RestClient.create("https://jsonplaceholder.typicode.com");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(client)).build();
		return factory.createClient(UserHttpClient.class);

	}

	@Bean
	CommandLineRunner todo(UserRestClient userRestClient) {

		return args -> {
			User user = userRestClient.getUserById(1);
			logger.info(""+user);

		};
	}
	@Bean
	CommandLineRunner httpClient(UserHttpClient userHttpClient) {

		return args -> {
			User user = userHttpClient.getUserById(1);

			logger.info(""+user);

		};
	}

}
