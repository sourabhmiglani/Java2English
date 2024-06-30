package com.example.JavaToEnglish.Service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
public class GetJavaFilePathsService {

    public void iterateoverJavaFiles(File folder,List<String> filePaths){

       File[] files = folder.listFiles();
       for(File file:files){
           if(file.isFile() && file.getName().endsWith(".java")){
               filePaths.add(file.getAbsolutePath());
           }
           else if(file.isDirectory()){
                iterateoverJavaFiles(file,filePaths);
           }
       }
    }
    public List<String> getjavaFilePaths(String folderPath){
        File fl = new File(folderPath);
        List<String> filePaths = new ArrayList<>();
        iterateoverJavaFiles(fl,filePaths);
        return filePaths;
     }

}
