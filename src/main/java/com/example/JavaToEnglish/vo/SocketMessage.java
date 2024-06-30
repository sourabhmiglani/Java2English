package com.example.JavaToEnglish.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocketMessage {

    Integer total;
    Integer completed;
    String fileName;
    String statusMessage;
}
