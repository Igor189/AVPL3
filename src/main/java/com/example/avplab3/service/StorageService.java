package com.example.avplab3.service;

import java.io.IOException;
import java.util.List;

public interface StorageService {
    List<String> getAllFiles();
    void addFile(String fileName) throws IOException;
    void deleteFile(String fileName) throws IOException;
    void renameFile(String oldFilename, String newFilename) throws IOException;
}
