package com.gcamp.fcmserver.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.gcamp.fcmserver.config.push.AndroidPushPeriodicNotification;
import com.gcamp.fcmserver.domain.PushVO;
import com.gcamp.fcmserver.mapper.PushMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

@Service("com.gcamp.fcmserver.service.NotificationService")
public class NotificationService {
	@Resource(name="com.gcamp.fcmserver.mapper.PushMapper")
	PushMapper pushMapper;
	
	@Autowired
    AndroidPushNotificationsService androidPushNotificationsService;
	
	@Autowired
	IosPushNotificationsService iosPushNotificationsService;
	
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void pushExcute() throws Exception{
    	List<PushVO> pushList = pushMapper.selectPushList();
    	
    	for(int i=0;i<pushList.size();i++) {
    		PushVO pushVo = pushList.get(i);
    		String device_os = pushVo.getDevice_os();
    		if("IOS".equals(device_os)) {
    			IosPushNotification(pushVo);
    		}else {
    			AndroidPushNotification(pushVo);
    		}
    	}
    }
    
    private int AndroidPushNotification(PushVO pushVo) throws InterruptedException, JsonSyntaxException {
    	int result = 0;
        String notifications = AndroidPushPeriodicNotification.PeriodicNotificationJson(pushVo);
        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();
        try{
            String firebaseResponse = pushNotification.get();
            JsonObject jsonObject = new JsonParser().parse(firebaseResponse).getAsJsonObject();
        }
        catch (InterruptedException e){
            logger.debug("got interrupted!");
            throw new InterruptedException();
        }
        catch (ExecutionException e){
            logger.debug("execution error!");
        }
    	
    	return result;
    }
    
    private void IosPushNotification(PushVO pushVo) throws Exception {
    	 try {
    		 iosPushNotificationsService.send(pushVo);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
    }
}
