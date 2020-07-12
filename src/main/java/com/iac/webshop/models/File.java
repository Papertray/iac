package com.iac.webshop.models;

import com.iac.webshop.helpers.ImageProvider;

import javax.persistence.*;
import java.io.IOException;

@Entity
public class File {
    @Transient
    public String fileData;

    @Transient
    private ImageProvider imageProvider;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "image")
    private Product product;

    @OneToOne(mappedBy = "image")
    private Category category;

    private String folderName;

    private String fileName;

    private String fileExtension;

    public String getFileData() throws IOException {
        return ImageProvider.loadImage(folderName, fileName);
    }

    public void setFileData(String fileData) throws IOException {
        // byte[] fileData = Base64.decodeBase64(base64String);
        ImageProvider.saveImage(fileData, folderName, fileName);
        this.fileData = fileData;
    }

    public ImageProvider getImageProvider() {
        return imageProvider;
    }

    public void setImageProvider(ImageProvider imageProvider) {
        this.imageProvider = imageProvider;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
