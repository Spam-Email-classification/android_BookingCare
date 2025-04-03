package com.example.bookingcare263;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingcare263.ui.uiuser.UserActivity;

public class ThongtinUser extends AppCompatActivity {

    private Button btndangxuat;
    private TextView txtchiase, txthosoyte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thongtin_user);

        anhxa();
        btndangxuat.setOnClickListener(v -> {
            UserActivity.iduser = null;
            Intent intent = new Intent(ThongtinUser.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        txtchiase.setOnClickListener(v -> shareApp());
        txthosoyte.setOnClickListener(v->{
            Intent intent = new Intent(ThongtinUser.this, Hosoyte.class);
            startActivity(intent);
        });
    }
    private void shareApp() {
        String packageName = getPackageName(); // Lấy package của ứng dụng
        String appLink = "https://play.google.com/store/apps/details?id=" + packageName;

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Chia sẻ ứng dụng");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hãy thử ứng dụng này: " + appLink);

        startActivity(Intent.createChooser(shareIntent, "Chia sẻ qua"));
    }


    private void anhxa() {
        btndangxuat = findViewById(R.id.btndangxuat);
        txtchiase = findViewById(R.id.txtchiase);
        txthosoyte = findViewById(R.id.txthosoyte);




    }
}