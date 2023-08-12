package com.googledrive.FileSystemJdbc.Repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.googledrive.FileSystemJdbc.Entity.Permission;

@Qualifier("permission")
@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    
}
