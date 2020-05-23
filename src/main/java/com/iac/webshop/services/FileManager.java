package com.iac.webshop.services;

import com.iac.webshop.helpers.ImageProvider;
import com.iac.webshop.models.File;
import com.iac.webshop.repositories.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileManager {
    @Autowired
    IFileRepository fileRepository;

    public void saveFileDB(File file) {
        if (file != null) {
            fileRepository.save(file);
        }
    }

    public void updateFile(File file, File oldFile) throws IOException {
        if (file != null) {
            if (oldFile != null) {
                fileRepository.delete(oldFile);
                ImageProvider.deleteImage(oldFile.getFolderName(), oldFile.getFileName());
            }
            fileRepository.save(file);
        }
    }
}
