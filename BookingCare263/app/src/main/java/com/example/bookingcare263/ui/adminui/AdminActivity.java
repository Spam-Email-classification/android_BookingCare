package com.example.bookingcare263.ui.adminui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.widget.Toast;


import com.example.bookingcare263.R;
import com.example.bookingcare263.listchuyenkhoa;

public class AdminActivity extends AppCompatActivity {

    CardView cvqlbs, cvqlbenhnhan, cvqlcsyt, cvqlchuyenkhoa, cvqllichhen, cvqldv;
    public static String roleadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);


        anhxa();
        roleadmin = "admin";
        checkAndRequestPermission();

        cvqlbs.setOnClickListener(view -> {
            Intent intent = new Intent(AdminActivity.this, adDanhsachBs.class);
            intent.putExtra("manager", "quanlybacsi");
            startActivity(intent);
        });
        cvqlbenhnhan.setOnClickListener(view -> {
            Intent intent = new Intent(AdminActivity.this, adDanhsachBs.class);
            intent.putExtra("manager", "quanlybenhnhan");
            startActivity(intent);
        });
        cvqlcsyt.setOnClickListener(view -> {
            Intent intent = new Intent(AdminActivity.this, adDanhsachBs.class);
            intent.putExtra("manager", "quanlycsyt");
            startActivity(intent);
        });
        cvqlchuyenkhoa.setOnClickListener(view ->{
            Intent intent = new Intent(AdminActivity.this, listchuyenkhoa.class);
            intent.putExtra("manager", "quanlychuyenkhoa");
            startActivity(intent);

        });




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        roleadmin = null;

    }

    private void checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 10);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Android 6+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Quyền đã được cấp!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bạn cần cấp quyền để tiếp tục.", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void anhxa() {
        cvqlbs = findViewById(R.id.cvqlybs);
        cvqlbenhnhan = findViewById(R.id.cvqlbenhnhan);
        cvqlcsyt = findViewById(R.id.cvqlcsyt);
        cvqlchuyenkhoa = findViewById(R.id.cvqlchuyenkhoa);

    }
}