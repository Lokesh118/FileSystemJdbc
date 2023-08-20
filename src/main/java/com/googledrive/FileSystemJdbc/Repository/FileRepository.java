package com.googledrive.FileSystemJdbc.Repository;

import java.util.List;

// import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.googledrive.FileSystemJdbc.Entity.File;

@Qualifier("file")
@Repository
public interface FileRepository extends JpaRepository<File, Integer>{
    List<File> findByOwnerId(String ownerId);
}
