package com.example.bookingcare263.ui.adminui;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookingcare263.R;

public class AdminActivity extends AppCompatActivity {

    CardView cvqlbs, cvqlbenhnhan, cvqlcsyt, cvqlchuyenkhoa, cvqllichhen, cvqldv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        anhxa();
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





    }

    private void anhxa() {
        cvqlbs = findViewById(R.id.cvqlybs);
        cvqlbenhnhan = findViewById(R.id.cvqlbenhnhan);
        cvqlcsyt = findViewById(R.id.cvqlcsyt);
        cvqlchuyenkhoa = findViewById(R.id.cvqlchuyenkhoa);
        cvqllichhen = findViewById(R.id.cvqlylichhen);
        cvqldv = findViewById(R.id.cvqldv);

    }
}