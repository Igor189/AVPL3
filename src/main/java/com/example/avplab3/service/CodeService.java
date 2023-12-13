package com.example.avplab3.service;

import java.io.IOException;

public interface CodeService {
    String getFileContent(String filename) throws IOException;
    void updateFileContent(String filename, String content) throws IOException;
}
