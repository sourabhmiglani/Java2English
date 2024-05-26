package com.example.JavaToEnglish;


import org.eclipse.jgit.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private CloneRepositoryService cloneRepositoryService;
    @Autowired
    private GetJavaFilePathsService getJavaFilePathsService;
    @GetMapping("/get")
    public String sayHi(@RequestParam String name){
        return "Hi " + name;
    }
    @PostMapping("/convert")
    public String  convertJavaToEnglish(@RequestParam String repoPath) throws IOException {

        System.out.println("Repository: " + repoPath);
        String cloneDirectoryPath = "C:\\Users\\soura\\Downloads\\JavaRepo";
        cloneRepositoryService.cloneRepo(cloneDirectoryPath,repoPath);
        List<String> javaFilePaths = new ArrayList<>();
        javaFilePaths = getJavaFilePathsService.getjavaFilePaths(cloneDirectoryPath);
//        String firstFileContent = Files.readString(Paths.get(javaFilePaths.get(0)));
//        System.out.println(firstFileContent);
    return "Code Converted and Stored!";
    }

}
