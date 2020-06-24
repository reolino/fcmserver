package com.gcamp.fcmserver.config.push;

import org.json.JSONException;
import org.json.JSONObject;

import com.gcamp.fcmserver.domain.PushVO;

/*
 *  Define push
 */
public class AndroidPushPeriodicNotification {
	public static String PeriodicNotificationJson(PushVO pushVo) throws JSONException {
        
	//single send
	JSONObject body = new JSONObject();
        
	String device_token = pushVo.getDevice_token();
        body.put("to", device_token);
        
        JSONObject notification = new JSONObject();
        notification.put("title",pushVo.getTitle());
        notification.put("body",pushVo.getBody());
        body.put("notification", notification);
		
	//multi send
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
