package com.googledrive.FileSystemJdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import com.googledrive.FileSystemJdbc.Entity.Permission;
import com.googledrive.FileSystemJdbc.Service.PermissionService;

@RestController
@Qualifier("permission")
@RequestMapping("/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @PostMapping("/addPermission")
    public ResponseEntity<String> AddPermission(@RequestBody Permission permission){
        System.out.println(permission.getFileId() + " " + permission.getUserId() + " " +permission.getReadAccess() + " " + permission.getWriteAccess());
        permissionService.addPermission(permission);
        return new ResponseEntity<String>("permission granted", HttpStatus.OK);
    }
}
