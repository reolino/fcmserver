package com.gcamp.fcmserver.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.gcamp.fcmserver.service.NotificationService;

@RestController
public class NotificationController {
    
    @Resource(name="com.gcamp.fcmserver.service.NotificationService")
    NotificationService notificationService;
	
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Scheduled(fixedRate = 60000) //60 second
    @GetMapping(value = "/push/excute")
	public void pushExcute() throws Exception { 
		notificationService.pushExcute(); 
	}
}
