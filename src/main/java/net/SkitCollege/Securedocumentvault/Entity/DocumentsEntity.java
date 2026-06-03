package net.SkitCollege.Securedocumentvault.Entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection="documents")
public class DocumentsEntity {

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public LocalDate getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        UploadDate = uploadDate;
    }

    @Id
    private String id;
    private LocalDate lastUpdatedDate;
    private int updateIntervalDays;
    private LocalDate UploadDate;
    private String ownerEmail;
    private LocalDate expiryDate;
    private String fileName;
    private String documentName;

    public int getUpdateIntervalDays() {
        return updateIntervalDays;
    }

    public void setUpdateIntervalDays(int updateIntervalDays) {
        this.updateIntervalDays = updateIntervalDays;
    }

    private String fileType;
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int expiryReminderDays;

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentname) {
        this.documentName = documentname;
    }

    
}
