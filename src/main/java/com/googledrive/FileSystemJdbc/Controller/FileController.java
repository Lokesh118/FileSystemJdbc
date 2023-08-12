package com.googledrive.FileSystemJdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googledrive.FileSystemJdbc.Entity.File;
import com.googledrive.FileSystemJdbc.Service.FileService;

@RestController
@Qualifier("file")
@RequestMapping("/api")
public class FileController {
    @Autowired
    private FileService fileService;
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestBody File file){
        fileService.addFile(file);
        return new ResponseEntity<String>("file upload successful", HttpStatus.OK);
    }
}
