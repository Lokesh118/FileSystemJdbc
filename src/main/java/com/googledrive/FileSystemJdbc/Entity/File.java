package com.googledrive.FileSystemJdbc.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.checkerframework.checker.units.qual.C;

import java.util.Date;

@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private int fileId;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "upload_date")
    private Date uploadDate;
    @Column(name = "bucket_name")
    private String bucketName;
    @Column(name = "file_size")
    private String fileSize;
    @Column(name = "owner_id")
    private String ownerId;

    public File(){

    }
    public File(String fileName, String bucketName, Date uploadDate, String fileSize, String ownerId){
        this.fileName = fileName;
        this.bucketName = bucketName;
        this.uploadDate = uploadDate;
        this.fileSize = fileSize;
        this.ownerId = ownerId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }


}
