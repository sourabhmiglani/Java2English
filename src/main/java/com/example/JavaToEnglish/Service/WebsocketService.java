package com.example.JavaToEnglish.Service;


import com.example.JavaToEnglish.vo.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebsocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessageToClient(SocketMessage socketMessage){
        this.simpMessagingTemplate.convertAndSend("/brk/j2e/client",socketMessage);
        System.out.println("Message sent");
    }

}
