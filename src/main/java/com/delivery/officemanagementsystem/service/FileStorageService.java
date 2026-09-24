package com.delivery.officemanagementsystem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    // application.properties-də: app.upload.dir=uploads
    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    /**
     * Faylı diskə yazır, DB-də saxlanmaq üçün nisbi yolu qaytarır (məs: "receipts/uuid.pdf").
     * Fayl boşdursa (istifadəçi seçməyibsə) null qaytarır.
     */
    public String store(MultipartFile file, String subDir) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            Path targetDir = Paths.get(uploadDir, subDir);
            Files.createDirectories(targetDir);

            String original = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : "");
            String ext = original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";
            String filename = UUID.randomUUID() + ext;

            Path targetPath = targetDir.resolve(filename);
            Files.copy(file.getInputStream(), targetPath);

            return subDir + "/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Fayl yüklənərkən xəta baş verdi: " + e.getMessage(), e);
        }
    }

    /**
     * DB-də saxlanan nisbi yolu brauzerdən açıla bilən public URL-ə çevirir.
     */
    public String toPublicUrl(String relativePath) {
        if (relativePath == null) {
            return null;
        }
        return "/uploads/" + relativePath;
    }
}
