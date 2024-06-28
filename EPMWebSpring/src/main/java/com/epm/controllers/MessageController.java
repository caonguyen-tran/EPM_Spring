/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.controllers;

import com.epm.pojo.Message;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.operation.ListenComplete;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Win11
 */
@RestController
public class MessageController {

    @PostMapping("/send-message")
    public ResponseEntity<Void> sendMessage(@RequestBody Message message) {
        DatabaseReference messagesRef = FirebaseDatabase.getInstance().getReference("messages");

        // Convert the Message object to a Map (as shown in the previous response)
        Map<String, Object> messageData = new HashMap<>();
        messageData.put("sender", message.getSender());
        messageData.put("content", message.getContent());
        messageData.put("createdDate", message.getCreatedDate());

        // Use setValue() with the Map and the ListenCompleteCallback
        messagesRef.push().setValue(messageData, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    // Handle the error
                    System.err.println("Data could not be saved: " + error.getMessage());
                } else {
                    // Data saved successfully
                    System.out.println("Data saved successfully.");
                }
            }
        });
        return null;
    }
}
