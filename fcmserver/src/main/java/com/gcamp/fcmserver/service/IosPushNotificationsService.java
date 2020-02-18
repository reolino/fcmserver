package com.gcamp.fcmserver.service;

import org.springframework.stereotype.Service;
import com.gcamp.fcmserver.domain.PushVO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/*
 *  IOS의 FCM 발송을 구현 
 */
@Service
public class IosPushNotificationsService {

	private static final String firebase_server_key="AAAA7o8UF98:APA91bEVK4W6e0ZcsTR8f5dwIU1ATaIKNMgsSMlZEBG1V3e8O_51syTP4OiOvm-W0dXUHNueACwZ2fe3urIGRPj-anvwgaJyz6aTle4flaFoZg8LrpibOYmQObbTxC8ovy3zt9j-DiLb";
    private static final String firebase_api_url="https://fcm.googleapis.com/fcm/send";

    public void send(PushVO pushVo) throws Exception {
    	URL url = new URL(firebase_api_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + firebase_server_key);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        JSONObject info = new JSONObject();

        info.put("body", pushVo.getBody());
        json.put("notification", info);
        json.put("to", pushVo.getDevice_token()); 
        
        try(OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8")){
            wr.write(json.toString());
            wr.flush();
        }catch(Exception e){
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        conn.disconnect();
    }
}
