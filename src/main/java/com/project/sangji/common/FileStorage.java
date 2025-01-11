package com.project.sangji.common;

import com.project.sangji.model.FileDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
            throw new RuntimeException("파일 업로드 중 오류 발생", e);
        }
        return fileList;
    }

    public ResponseEntity<InputStreamResource> downloadFile(String oFile, String nFile) {
        File file = new File(uploadDir + nFile);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamResource resource = new InputStreamResource(fileInputStream);

            HttpHeaders headers = new HttpHeaders();
            String encodedFileName =
                    URLEncoder.encode(oFile, StandardCharsets.UTF_8).replaceAll("\\+",
                            "%20");
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public void deleteFile(String fileName) {
        File file = new File(uploadDir + fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
