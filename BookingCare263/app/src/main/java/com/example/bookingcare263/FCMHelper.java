package com.example.bookingcare263;

import android.content.Context;

import androidx.annotation.NonNull;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import android.util.Base64;

import java.util.Date;
import java.util.stream.Collectors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FCMHelper {
    private static final String SCOPE = "https://www.googleapis.com/auth/firebase.messaging";

    public static String getAccessToken(Context context) throws Exception {
        InputStream inputStream = context.getAssets().open("bookingcare263-firebase.json");
        String json = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));

        JSONObject jsonObject = new JSONObject(json);
        String clientEmail = jsonObject.getString("client_email");
        String privateKey = jsonObject.getString("private_key").replace("\\n", "\n");

        Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(
                        Base64.decode(privateKey
                                .replace("-----BEGIN PRIVATE KEY-----", "")
                                .replace("-----END PRIVATE KEY-----", "")
                                .replaceAll("\\s+", ""), Base64.DEFAULT)

                )));

        long now = System.currentTimeMillis();
        String jwt = JWT.create()
                .withIssuer(clientEmail)
                .withAudience("https://oauth2.googleapis.com/token")
                .withClaim("scope", SCOPE)
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + 3600 * 1000))
                .sign(algorithm);

        // Exchange JWT for access token
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer")
                .add("assertion", jwt)
                .build();

        Request request = new Request.Builder()
                .url("https://oauth2.googleapis.com/token")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        return new JSONObject(responseBody).getString("access_token");
    }
}


