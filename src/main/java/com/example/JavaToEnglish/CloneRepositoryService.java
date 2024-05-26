package com.example.JavaToEnglish;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CloneRepositoryService {

public void cloneRepo(String localFolderPath,String repoUrl){
    // Clone repository
    try {
        File localRepoDir = new File(localFolderPath);
        CloneCommand cloneCommand = Git.cloneRepository()
                .setURI(repoUrl)
                .setDirectory(localRepoDir)
                .setBranch("master"); // specify the branch to clone, in this case, "master"
        Git git = cloneCommand.call();

        // Print a success message
        System.out.println("Repository cloned successfully.");

        // Close the Git object
        git.close();
    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("Failed to clone repository.");
    }
}
}
