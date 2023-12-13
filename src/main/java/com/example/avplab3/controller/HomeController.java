package com.example.avplab3.controller;

import com.example.avplab3.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class HomeController {
    private final StorageService storageService;

    @GetMapping()
    public String getAllFiles(Model model){
        model.addAttribute("files", storageService.getAllFiles());
        return "home";
    }

    @PostMapping()
    public String addFile(@RequestParam("filename") String filename, RedirectAttributes redirectAttributes){
        try {
            storageService.addFile(filename);
            redirectAttributes.addFlashAttribute("message", "Файл успешно добавлен!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute(
                    "message", "Ошибка при добавлении файла: " + e.getMessage());
        }

        return "redirect:/";
    }

    @DeleteMapping("/{filename}")
    public String deleteFile(@PathVariable("filename") String filename, RedirectAttributes redirectAttributes){
        try {
            storageService.deleteFile(filename);
            redirectAttributes.addFlashAttribute("message", "Файл успешно удален!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute(
                    "message", "Ошибка при удалении файла: " + e.getMessage());
        }

        return "redirect:/";
    }

    @PutMapping("/{filename}")
    public String renameFile(@PathVariable("filename") String filename,
                                 @RequestParam("newFilename") String newFilename,
                             RedirectAttributes redirectAttributes){
        try {
            storageService.renameFile(filename, newFilename);
            redirectAttributes.addFlashAttribute("message", "Файл успешно переименован!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute(
                    "message", "Ошибка при переименовании файла: " + e.getMessage());
        }

        return "redirect:/";
    }
}
