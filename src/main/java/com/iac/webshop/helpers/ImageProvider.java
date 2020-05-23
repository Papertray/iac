package com.iac.webshop.helpers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageProvider {
    public final static String rootFolder = "C://Users/Admin/Desktop/";

    public static Path getFullPath(String folderName, String fileName) {
        return Paths.get(rootFolder + folderName + "/" + fileName);
    }

    public static String getPathString(String folderName, String fileName) {
        return (rootFolder + folderName + "/" + fileName);
    }

    public static void saveImage(String fileData, String folderName, String fileName) throws IOException {
        Files.createDirectories(Paths.get(rootFolder + folderName + "/"));
        Files.write(new File(getPathString(folderName, fileName)).toPath(), Base64.decodeBase64(fileData));
    }

    public static String loadImage(String folderName, String fileName) throws IOException {
        return Base64.encodeBase64String(Files.readAllBytes(getFullPath(folderName, fileName)));
    }

    public static void deleteImage(String folderName, String fileName) throws IOException {
        Files.delete(getFullPath(folderName, fileName));
    }
}
