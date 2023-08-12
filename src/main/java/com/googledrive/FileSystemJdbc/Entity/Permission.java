package com.googledrive.FileSystemJdbc.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private int PermissionId;
    @Column(name = "file_id")
    private String fileId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "read_access")
    private String readAccess;
    @Column(name = "write_access")
    private String writeAccess;
    public Permission() {
    }
    public int getPermissionId() {
        return PermissionId;
    }
    public void setPermissionId(int permissionId) {
        PermissionId = permissionId;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getReadAccess() {
        return readAccess;
    }
    public void setReadAccess(String readAccess) {
        this.readAccess = readAccess;
    }
    public String getWriteAccess() {
        return writeAccess;
    }
    public void setWriteAccess(String writeAccess) {
        this.writeAccess = writeAccess;
    }

    

}
