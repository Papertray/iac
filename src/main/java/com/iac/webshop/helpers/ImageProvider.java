package com.iac.webshop.helpers;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@Service
public class ImageProvider {
    public final static String rootFolder = "C://Users/Boris Blom/Desktop/";

    public static void CreateImage(String folder, String id) {
        BufferedImage bImage = null;
        try {
            ImageIO.write(bImage, "jpg", new File(rootFolder + folder + "/" + id + ".png"));
        } catch (IOException e) {
        }
    }

    public static BufferedImage ReadImage(String folder, String id) {
        try
        {
            return ImageIO.read(new File(rootFolder + folder + "/" + id + ".png"));
        }
        catch (IOException e)
        {
            return null;
        }
    }

    public static String encoder(String imagePath) {
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            String base64Image = "";
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
            return base64Image;
        } catch (FileNotFoundException e) {
            System.out.println("Image not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return null;
    }

    public static void decoder(String base64Image, String folder) {
        // File.(rootFolder+folder);
        try (FileOutputStream imageOutFile = new FileOutputStream(rootFolder+folder)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            File file = new File(rootFolder+folder);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found " + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }
}
