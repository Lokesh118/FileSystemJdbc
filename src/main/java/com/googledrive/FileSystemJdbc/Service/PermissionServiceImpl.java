package com.googledrive.FileSystemJdbc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.googledrive.FileSystemJdbc.Entity.Permission;
import com.googledrive.FileSystemJdbc.Repository.PermissionRepository;

@Service
@Qualifier("permission")
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    PermissionRepository permissionRepository; 

    @Override
    public Permission addPermission(Permission permission){
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getPermissions(){
        return permissionRepository.findAll();
    }

}
