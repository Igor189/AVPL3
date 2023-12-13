package com.example.avplab3.service.impl;

import com.example.avplab3.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {
    @Value("${html.dir}")
    private String dirPath;

    @Override
    public List<String> getAllFiles() {
        File folder = new File(dirPath);
        return Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .map(File::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void addFile(String fileName) throws IOException {
        Path filePath = Paths.get(dirPath, fileName);
        Files.createFile(filePath);
    }

    @Override
    public void deleteFile(String fileName) throws IOException {
        Files.delete(Paths.get(dirPath, fileName));
    }

    @Override
    public void renameFile(String oldFilename, String newFilename) throws IOException {
        Path oldFilePath = Paths.get(dirPath, oldFilename);
        Files.move(oldFilePath, oldFilePath.resolveSibling(newFilename));
    }
}
