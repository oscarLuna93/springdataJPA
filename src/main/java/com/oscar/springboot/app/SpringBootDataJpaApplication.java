package com.oscar.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oscar.springboot.app.models.service.IUploadService;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner{

	@Autowired
	IUploadService uploadService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		uploadService.deleteAll();
		uploadService.init();
	}

}
