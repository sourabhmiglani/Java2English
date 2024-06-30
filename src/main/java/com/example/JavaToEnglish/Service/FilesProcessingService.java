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
    private CallLlmService callLlmService;

    @Autowired
    private WebsocketService websocketService;

    public void processFiles(List<String> javaFilePaths){
        SocketMessage socketMessage = new SocketMessage();
        int filesProcessed = 0;
        int totalFiles = javaFilePaths.size();
        socketMessage.setTotal(totalFiles);
        socketMessage.setCompleted(filesProcessed);

        for (String filePath : javaFilePaths) {
            socketMessage.setFileName(filePath);
            try {
                String fileContent = Files.readString(Paths.get(filePath));
                String fileSummary = ""; // Process the file content as needed
                Thread.sleep(100); // Simulate processing time
                socketMessage.setStatusMessage("File processed successfully");

            } catch (Exception e) {
                // Handle the exception and continue processing the next file
                e.printStackTrace();
                socketMessage.setStatusMessage("Error processing file: " + e.getMessage());
            }
            filesProcessed++;
            socketMessage.setCompleted(filesProcessed);
            websocketService.sendMessageToClient(socketMessage);
        }
    }

}
