package com.example.bookingcare263.ui.uiuser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingcare263.DieuKhoan;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.LienHe;
import com.example.bookingcare263.LoginActivity;
import com.example.bookingcare263.R;
import com.example.bookingcare263.UserActivity;
import com.example.bookingcare263.model.accout;

public class ThongtinUser extends AppCompatActivity {

    private Button btndangxuat;
    private TextView txtchiase, txthosoyte;
    private TextView txthotenuser, txtsdtuser;
    private TextView txtqlydiachi;
    private TextView txtdonhang;
    private TextView txtdieukhoan;
    private TextView lienhehotro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        txthosoyte.setOnClickListener(v -> {
            Intent intent = new Intent(ThongtinUser.this, Hosoyte.class);
            startActivity(intent);
        });

        txtdieukhoan.setOnClickListener(v -> {
            Intent intent = new Intent(ThongtinUser.this, DieuKhoan.class);
            startActivity(intent);
        });
        lienhehotro.setOnClickListener(v -> {
            Intent intent = new Intent(ThongtinUser.this, LienHe.class);
            startActivity(intent);
        });

        // get user by iduser

        FirebaseHelper.getaccbyid(UserActivity.iduser, new FirebaseCallBack<accout>() {
            @Override
            public void onSuccess(accout data) {
                txthotenuser.setText(data.getName());
                txtsdtuser.setText(data.getPhone());
            }

            @Override
            public void onFailed(String message) {

            }
        });

    }



    private void shareApp() {
        String packageName = getPackageName(); // Get the package of the app
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
        txtqlydiachi = findViewById(R.id.txtqlydiachi);
        txtdonhang = findViewById(R.id.txtdonhang);
        txtdieukhoan = findViewById(R.id.txtdieukhoan);
        lienhehotro = findViewById(R.id.lienhehotro);
        txthosoyte = findViewById(R.id.tv_hotenuser);
        txtsdtuser = findViewById(R.id.txt_sdtuser);
    }
}
