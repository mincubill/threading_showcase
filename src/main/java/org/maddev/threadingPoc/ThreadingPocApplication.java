package org.maddev.threadingPoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ThreadingPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadingPocApplication.class, args);
	}

}
