package com.example.JavaToEnglish;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private CloneRepositoryService cloneRepositoryService;
    @GetMapping("/get")
    public String sayHi(@RequestParam String name){
        return "Hi " + name;
    }
    @PostMapping("/convert")
    public String  convertJavaToEnglish(@RequestParam String repoPath){

        System.out.println("Repository: " + repoPath);
        String cloneDirectoryPath = "C:\\Users\\soura\\Downloads\\JavaRepo";
        cloneRepositoryService.cloneRepo(cloneDirectoryPath,repoPath);

    return "Code Converted and Stored!";
    }

}
