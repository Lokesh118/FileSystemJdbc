package com.googledrive.FileSystemJdbc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.googledrive.FileSystemJdbc.Entity.File;

@Qualifier("file")
public interface FileService {
    public void addFile(File file);
    public List<File> getFiles();
}
