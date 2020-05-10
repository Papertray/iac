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
//    public static void CreateImage(String folder, String id) {
//        BufferedImage bImage = null;
//        try {
//            ImageIO.write(bImage, "jpg", new File(rootFolder + folder + "/" + id + ".png"));
//        } catch (IOException e) {
//        }
//    }

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
//
//    public static BufferedImage ReadImage(String folder, String id) {
//        try
//        {
//            return ImageIO.read(new File(rootFolder + folder + "/" + id + ".png"));
//        }
//        catch (IOException e)
//        {
//            return null;
//        }
//    }
//
//    public static String encoder(String imagePath) {
//        File file = new File(imagePath);
//        try (FileInputStream imageInFile = new FileInputStream(file)) {
//            // Reading a Image file from file system
//            String base64Image = "";
//            byte imageData[] = new byte[(int) file.length()];
//            imageInFile.read(imageData);
//            base64Image = Base64.getEncoder().encodeToString(imageData);
//            return base64Image;
//        } catch (FileNotFoundException e) {
//            System.out.println("Image not found " + e);
//        } catch (IOException ioe) {
//            System.out.println("Exception while reading the Image " + ioe);
//        }
//        return null;
//    }
//
//    public static void decoder(String base64Image, String folder) {
//        // File.(rootFolder+folder);
//        try (FileOutputStream imageOutFile = new FileOutputStream(rootFolder+folder)) {
//            // Converting a Base64 String into Image byte array
//            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
//            File file = new File(rootFolder+folder);
//            imageOutFile.write(imageByteArray);
//        } catch (FileNotFoundException e) {
//            System.out.println("Image not found " + e);
//        } catch (IOException ioe) {
//            System.out.println("Exception while reading the Image " + ioe);
//        }
//    }
}
