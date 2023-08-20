package com.googledrive.FileSystemJdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.rpc.context.AttributeContext.Response;
import com.googledrive.FileSystemJdbc.Entity.File;
import com.googledrive.FileSystemJdbc.Service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@Qualifier("file")
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {
    @Autowired
    private ResourceLoader resourceLoader;
    private FileService fileService;
    public FileController(ResourceLoader resourceLoader, FileService fileService){
        this.resourceLoader = resourceLoader;
        this.fileService = fileService;
   }
    
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file,@RequestPart("userId") String userId){
        ResponseEntity<String> response = uploadhelper(userId, file);
        if(response.getStatusCode() == HttpStatus.OK){
            LocalDate localDate = LocalDate.now();
            ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
            Date date = Date.from(zonedDateTime.toInstant());
            long filesize = file.getSize();
            String fileName = file.getOriginalFilename();
            String bucketName = "root_storage_drive";
            String fileSize = BytesToKBMBGBConv(filesize);
            File filemember = new File(fileName,bucketName,date,fileSize,userId);
            fileService.addFile(filemember);
            return new ResponseEntity<String>("file upload successful", HttpStatus.OK);
        }else{
            return response;
        }
    }

    @GetMapping("/allFiles")
    public ResponseEntity<List<File>> allfiles(@RequestParam("userId") String userId){
        List<File> userFiles = fileService.getUserFiles(userId);
        return new ResponseEntity<List<File>>(userFiles, HttpStatus.OK);
    }
    
    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> download(@RequestParam("fileId") String fileId) throws IOException{
        List<File> files = fileService.getFiles();
        int file_id = Integer.parseInt(fileId);
        for(File file: files){
            if(file.getFileId() == file_id){
                String bucketName = file.getBucketName();
                String ownerId = file.getOwnerId();
                String fileName = ownerId + "/" + file.getFileName();
                System.out.println(fileName +" " + bucketName + " " + ownerId);
                InputStream inputStream = resourceLoader.getResource("classpath:service_account_key.json").getInputStream();
                Credentials credentials = GoogleCredentials.fromStream(inputStream);
                Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId("drive-spring-180102045").build().getService();
                BlobId blobId = BlobId.of(bucketName, fileName);
                Blob blob = storage.get(blobId);
                if(blob == null){
                    System.out.println("blob is empty");
                    return ResponseEntity.notFound().build();
                // return new ResponseEntity<String>(fileName,HttpStatus.OK);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType(blob.getContentType()));
                headers.setContentDispositionFormData("attachment",fileName);
                byte[] fileContent = blob.getContent();
                ByteArrayResource resource = new ByteArrayResource(fileContent);
                return ResponseEntity.ok().headers(headers).contentLength(fileContent.length).body(resource);
            }
        }
        return ResponseEntity.notFound().build();
    }
    private String BytesToKBMBGBConv(double size){
        long num = 1024;
        long gb = num*num*num;
        long mb = num*num;
        long kb = num;
        if(size >= gb){
            double sz = size/gb;
            return Double.toString(sz) + " GB";
        }else if(size >= mb){
            double sz = size/mb;
            return Double.toString(sz) + " MB";
        }else if(size >= kb){
            double sz = size/kb;
            return Double.toString(sz) + " KB";
        }else{
            return Double.toString(size) + " B";
        }
    }
    private ResponseEntity <String> uploadhelper(String userId, MultipartFile file){
        if(file == null){
            return new ResponseEntity<String>("file is null", HttpStatus.BAD_REQUEST);
        }
        try{
            String filename =  userId + "/" + file.getOriginalFilename();
            InputStream inputStream = resourceLoader.getResource("classpath:service_account_key.json").getInputStream();
            Credentials credentials = GoogleCredentials.fromStream(inputStream);
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId("drive-spring-180102045").build().getService();
            Bucket bucket = storage.get("root_storage_drive");
            Blob blob = bucket.create(filename,file.getInputStream(),file.getContentType());
            String publicURL = blob.getMediaLink();
            return new ResponseEntity<String>(publicURL, HttpStatus.OK);
        }catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Failed to upload the file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
