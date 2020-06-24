It is a web service that sends Android and IOS push based on Firebase.
I am using Spring Boot and it runs every 60 seconds.

- How to use.
1. Enter the Firebase site and get an authentication key.
2. Install the authentication key on Android and IOS. Please refer to the official documentation.
3. Get the server key
Put it in fcm_push_key part of AndroidPushNotificationsService.java and IosPushNotificationsService.java.
4. Modify db connection information in application.properties.
5. Modify the query in pushMapper.xml.

- Order of execution.
1. Look up the table you want to send the push to.
2. Send a push.
3. Record the push log. If it fails, send it one more time.
