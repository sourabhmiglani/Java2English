package com.example.JavaToEnglish.Controller;

import com.example.JavaToEnglish.Service.*;
import com.example.JavaToEnglish.vo.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CloneRepositoryService cloneRepositoryService;

    @Autowired
    private GetJavaFilePathsService getJavaFilePathsService;



    @Autowired
    FilesProcessingService filesProcessingService;

    @GetMapping("/get")
    public String sayHi(@RequestParam String name) {
        return "Hi " + name;
    }

    @PostMapping("/convert")
    @CrossOrigin
    public String convertJavaToEnglish(@RequestParam String repoPath, @RequestParam String gptVersion) throws IOException {
        System.out.println("Repository: " + repoPath);
        String cloneDirectoryPath = "C:\\Users\\soura\\Downloads\\JavaRepo";
        cloneRepositoryService.cloneRepo(cloneDirectoryPath, repoPath);
        List<String> javaFilePaths = getJavaFilePathsService.getjavaFilePaths(cloneDirectoryPath);

        filesProcessingService.processFiles(javaFilePaths);

        return "Code Converted and Stored!";
    }
}
