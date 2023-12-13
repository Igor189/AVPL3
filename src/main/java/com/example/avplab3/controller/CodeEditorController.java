package com.example.avplab3.controller;

import com.example.avplab3.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/code-editor")
@RequiredArgsConstructor
public class CodeEditorController {
    private final CodeService codeService;

    @GetMapping("/{filename}")
    public String getContent(@PathVariable("filename") String filename, Model model){
        try {
            String content = codeService.getFileContent(filename);
            model.addAttribute("fileContent", content);
            model.addAttribute("file", filename);
        } catch (IOException e) {
            model.addAttribute("message", "Ошибка чтения файла: " + e.getMessage());
        }

        return "code-editor";
    }

    @PutMapping("/{filename}")
    public String updateContent(@PathVariable("filename") String filename,
                                @RequestParam("fileContent") String fileContent,
                                RedirectAttributes redirectAttributes){
        try {
            codeService.updateFileContent(filename, fileContent);
            redirectAttributes.addFlashAttribute("message",
                    "Файл успешно сохранен!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message",
                    "Ошибка при сохранении файла: " + e.getMessage());
        }

        return "redirect:/code-editor/" + filename;
    }
}
