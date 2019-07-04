package com.leandrorego.elivro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//chamada do agendamento
@EnableScheduling
public class ElivroApplication {	
	
	public static void main(String[] args) {
		SpringApplication.run(ElivroApplication.class, args);
		
	}
}


