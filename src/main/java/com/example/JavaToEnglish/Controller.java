package com.example.JavaToEnglish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private CallLlmService callLlmService;

    @GetMapping("/get")
    public String sayHi(@RequestParam String name){
        return "Hi " + name;
    }

    @PostMapping("/convert")
    public String convertJavaToEnglish(@RequestParam String repoPath,@RequestParam String gptVersion) throws IOException {
        System.out.println("Repository: " + repoPath);
        String cloneDirectoryPath = "C:\\Users\\soura\\Downloads\\JavaRepo";
        cloneRepositoryService.cloneRepo(cloneDirectoryPath, repoPath);
        List<String> javaFilePaths = new ArrayList<>();
        javaFilePaths = getJavaFilePathsService.getjavaFilePaths(cloneDirectoryPath);

        javaFilePaths.forEach(file -> {
            try {
                String fileContent = Files.readString(Paths.get(file));
//               System.out.println(fileContent);

//                 System.out.println(file);
                // Uncomment the f
                // ollowing lines to use the LLM service
                 String fileSummary = "";
                 if(file.endsWith("CabLocationController.java")){
                     fileSummary = callLlmService.callLlm(fileContent,gptVersion);
                      System.out.println(fileSummary);
                 }

            } catch (IOException e) {
                // Handle the exception
                e.printStackTrace();
                System.err.println("Error reading file: " + file);
            }
        });

        return "Code Converted and Stored!";
    }
}
