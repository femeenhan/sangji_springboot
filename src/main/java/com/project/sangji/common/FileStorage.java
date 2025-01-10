package com.project.sangji.common;

import com.project.sangji.model.FileDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStorage {
    private final String uploadDir = "upload/";

    @PostConstruct
    public void init() {
        File file = new File(uploadDir);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public List<FileDTO> fileUpload(MultipartFile[] files) {
        List<FileDTO> fileList = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }
                String unigueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path nFile = Paths.get(uploadDir + unigueFileName);

                Files.copy(file.getInputStream(), nFile);
                fileList.add(new FileDTO(file.getOriginalFilename(), unigueFileName));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileList;
    }
}
