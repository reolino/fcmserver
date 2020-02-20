package com.gcamp.fcmserver.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gcamp.fcmserver.config.push.HeaderRequestInterceptor;

@Service
public class AndroidPushNotificationsService {

	private static final String firebase_server_key="AAAA7o8UF98:APA91bEVK4W6e0ZcsTR8f5dwIU1ATaIKNMgsSMlZEBG1V3e8O_51syTP4OiOvm-W0dXUHNueACwZ2fe3urIGRPj-anvwgaJyz6aTle4flaFoZg8LrpibOYmQObbTxC8ovy3zt9j-DiLb";
    private static final String firebase_api_url="https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new HeaderRequestInterceptor("Authorization",  "key=" + firebase_server_key));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json; UTF-8 "));
        restTemplate.setInterceptors(interceptors);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));//한글깨짐 처리

        String firebaseResponse = restTemplate.postForObject(firebase_api_url, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
