package br.com.frota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;


@SpringBootApplication
public class FrotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrotaApplication.class, args);

	}

	@PostConstruct
	public void loadData() {
	}

}