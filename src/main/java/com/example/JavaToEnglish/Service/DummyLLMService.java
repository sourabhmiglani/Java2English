package com.example.JavaToEnglish.Service;

import com.example.JavaToEnglish.vo.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DummyLLMService {
    @Autowired
    private CallLlmService callLlmService;

    @Autowired
    private WebsocketService websocketService;

    private AtomicInteger filesProcessed = new AtomicInteger(0);
    @Async("taskExecutor")
    public void simulateProcessing(String filePath,int totalFiles)  {
        System.out.println(Thread.currentThread().getName());
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setTotal(totalFiles);
        socketMessage.setFileName(filePath);
        try {
            String fileContent = Files.readString(Paths.get(filePath));
            Thread.sleep(1000);
            socketMessage.setStatusMessage("File processed successfully");
        }
        catch (Exception e ){
            e.printStackTrace();
            socketMessage.setStatusMessage("Error processing file: " + e.getMessage());
        }
        int currFilesProcessed = this.filesProcessed.getAndIncrement();
        socketMessage.setCompleted(currFilesProcessed);
        websocketService.sendMessageToClient(socketMessage);
    }
}
