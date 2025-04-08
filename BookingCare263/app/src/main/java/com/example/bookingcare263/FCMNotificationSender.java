package com.example.bookingcare263;

import static com.example.bookingcare263.FCMHelper.getAccessToken;

import android.content.Context;
import android.util.Log;



import org.json.JSONObject;

import java.io.InputStream;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FCMNotificationSender {
    public static void sendNotification(Context context, String token, String title, String body) {
        new Thread(() -> {
            try {
                String accessToken = getAccessToken(context);

                JSONObject message = new JSONObject();
                message.put("token", token);

                JSONObject notification = new JSONObject();
                notification.put("title", title);
                notification.put("body", body);
                message.put("notification", notification);

                JSONObject json = new JSONObject();
                json.put("message", message);

                String projectId = "bookingcare263"; // Thay bằng project_id từ service-account.json
                String fcmUrl = "https://fcm.googleapis.com/v1/projects/" + projectId + "/messages:send";

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = RequestBody.create(json.toString(), MediaType.parse("application/json"));

                Request request = new Request.Builder()
                        .url(fcmUrl)
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .addHeader("Content-Type", "application/json")
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();
                Log.d("FCM", "Response: " + response.body().string());

            } catch (Exception e) {
                Log.e("FCM", "Error sending FCM", e);
            }
        }).start();
    }


}
