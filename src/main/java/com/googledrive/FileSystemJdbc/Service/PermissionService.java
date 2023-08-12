package com.googledrive.FileSystemJdbc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.googledrive.FileSystemJdbc.Entity.Permission;

@Qualifier("permission")
public interface PermissionService {
    public Permission addPermission(Permission permission);
    public List<Permission> getPermissions();
}
