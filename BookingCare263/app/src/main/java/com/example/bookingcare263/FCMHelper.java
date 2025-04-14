package com.example.bookingcare263;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.android.gms.tasks.Task;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.functions.FirebaseFunctions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import okhttp3.*;

public class FCMHelper {

    private static final String TAG = "FCM";



    public static Task<String> sendFCM(String token, String title, String body) {
        FirebaseFunctions functions = FirebaseFunctions.getInstance();

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("title", title);
        data.put("body", body);

        return functions
                .getHttpsCallable("sendNotification")
                .call(data)
                .continueWith(task -> {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    Map<String, Object> result = (Map<String, Object>) task.getResult().getData();
                    return result.get("success").toString();
                });
    }



    public static void sendNotification(Context context, String targetToken, String title, String body) {
        new Thread(() -> {
            try {
                String accessToken = getAccessToken(context);

                Log.d("FCM", "Sending to token: " + targetToken);

                JSONObject message = new JSONObject();
                JSONObject notification = new JSONObject();
                notification.put("title", title);
                notification.put("body", body);

                JSONObject data = new JSONObject();
                data.put("click_action", "FLUTTER_NOTIFICATION_CLICK");

                JSONObject json = new JSONObject();
                json.put("message", new JSONObject()
                        .put("token", targetToken)
                        .put("notification", notification)
                        .put("data", data));

                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = RequestBody.create(
                        json.toString(),
                        MediaType.parse("application/json")
                );

                Request request = new Request.Builder()
                        .url("https://fcm.googleapis.com/v1/projects/bookingcare263/messages:send")
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .post(requestBody)
                        .build();

                Response response = client.newCall(request).execute();
                Log.d("FCM", "Response: " + response.body().string());

            } catch (Exception e) {
                Log.e("FCM", "Lỗi gửi thông báo: " + e.getMessage(), e);
            }
        }).start();
    }

    // Lấy access token từ file service-account.json
    public static String getAccessToken(Context context) throws Exception {
        InputStream stream = context.getAssets().open("service-account.json");

        GoogleCredentials credentials = GoogleCredentials
                .fromStream(stream)
                .createScoped(Collections.singleton("https://www.googleapis.com/auth/firebase.messaging"));

        credentials.refreshIfExpired(); // Tự refresh nếu hết hạn

        return credentials.getAccessToken().getTokenValue();
    }
    // Tạo private key từ chuỗi PEM



    // Gửi message đến Firebase Cloud Messaging
}
