package com.googledrive.FileSystemJdbc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping
    public String home(){
        return "hello world";
    }
}
