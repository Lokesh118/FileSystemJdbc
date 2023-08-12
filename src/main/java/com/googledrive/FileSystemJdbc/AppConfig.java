package com.googledrive.FileSystemJdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.googledrive.FileSystemJdbc.Entity.File;
import com.googledrive.FileSystemJdbc.Entity.Permission;

@Configuration
public class AppConfig {
    @Bean
    public File file(){
        return new File();
    }
    @Bean
    public Permission permission(){
        return new Permission();
    }
}
