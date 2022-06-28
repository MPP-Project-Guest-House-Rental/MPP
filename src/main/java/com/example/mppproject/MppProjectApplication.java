package com.example.mppproject;

import com.example.mppproject.utility.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MppProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MppProjectApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Greetings from MPP project!!!!";
	}


}
