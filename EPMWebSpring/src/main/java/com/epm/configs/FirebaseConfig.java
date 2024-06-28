/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epm.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Win11
 */
@Configuration
public class FirebaseConfig {

    public void initializeFirebase() throws IOException {
        String serviceAccountPath = System.getenv("D:/Workspace/EPM_Spring");
        FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://epmproject-21.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

}
