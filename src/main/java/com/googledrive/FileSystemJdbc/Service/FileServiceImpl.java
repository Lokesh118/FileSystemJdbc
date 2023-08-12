package com.googledrive.FileSystemJdbc.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.googledrive.FileSystemJdbc.Entity.File;
import com.googledrive.FileSystemJdbc.Repository.FileRepository;

@Service
@Qualifier("file")
public class FileServiceImpl implements FileService{
    @Autowired
    FileRepository fileRepository;

    @Override
    public void addFile(File file){
        fileRepository.save(file);
    }

    @Override
    public List<File> getFiles(){
        return fileRepository.findAll();
    }    
}
