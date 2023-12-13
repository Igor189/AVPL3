package com.example.avplab3.service.impl;

import com.example.avplab3.service.CodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CodeServiceImpl implements CodeService {
    @Value("${html.dir}")
    private String dirPath;

    @Override
    public String getFileContent(String filename) throws IOException {
        Path filePath = Paths.get(dirPath, filename);
        return Files.readString(filePath);
    }

    @Override
    public void updateFileContent(String filename, String content) throws IOException {
        Path filePath = Paths.get(dirPath, filename);
        Files.writeString(filePath, content);
    }
}
