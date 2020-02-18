package com.gcamp.fcmserver.config.push;

import org.json.JSONException;
import org.json.JSONObject;

import com.gcamp.fcmserver.domain.PushVO;

/*
 *  PUSH의 형태를 정의한다 
 */
public class AndroidPushPeriodicNotification {
	public static String PeriodicNotificationJson(PushVO pushVo) throws JSONException {
        
		//단일 발송
		JSONObject body = new JSONObject();
        
		String device_token = pushVo.getDevice_token();
        body.put("to", device_token);
        
        JSONObject notification = new JSONObject();
        notification.put("title",pushVo.getTitle());
        notification.put("body",pushVo.getBody());
        body.put("notification", notification);
		
		//복수 발송(같은 메세지를 발송해야 하는 경우 아래 구문을 사용하세요)
        /*
        String device_token = pushVo.getDevice_token();
        String sampleData[] = {device_token};
        
        JSONObject body = new JSONObject();
        List<String> tokenlist = new ArrayList<String>();
        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }
        
        JSONArray array = new JSONArray();
        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }
        
        body.put("registration_ids", array);
        JSONObject notification = new JSONObject();
        notification.put("title",pushVo.getTitle());
        notification.put("body",pushVo.getBody());
        body.put("notification", notification);
        */

        return body.toString();
    }
}
