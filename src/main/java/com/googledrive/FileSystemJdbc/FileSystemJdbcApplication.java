package com.googledrive.FileSystemJdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.googledrive.FileSystemJdbc.Entity")
@EnableJpaRepositories(basePackages = "com.googledrive.FileSystemJdbc.Repository")
public class FileSystemJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileSystemJdbcApplication.class, args);
	}

}
