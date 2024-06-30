package com.example.JavaToEnglish.Service;


import com.example.JavaToEnglish.vo.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FilesProcessingService {


    @Autowired
    private DummyLLMService dummyLLMService;

    public void processFiles(List<String> javaFilePaths){

        int filesProcessed = 0;
        int totalFiles = javaFilePaths.size();


        for (String filePath : javaFilePaths) {

            dummyLLMService.simulateProcessing(filePath,totalFiles);
        }
    }

}
