package com.example.bookingcare263.ui.uicsyt;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookingcare263.ChitietCSYT;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.ui.DSchuyenkhoaCSYT;
import com.example.bookingcare263.ui.adminui.SuaCSYT;

public class ThongtinCSYT extends AppCompatActivity {
    Toolbar toolbar;
    TextView txttenbenhvien;
    Button btnsuathongtin, btnqlyckkham;
    Cosoyte csyt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thongtin_csyt);


        anhxa();
        Intent intent = getIntent();
        csyt = (Cosoyte) intent.getSerializableExtra("cosoyte");
        txttenbenhvien.setText(csyt.getName());

        btnsuathongtin.setOnClickListener(e->{
            Intent intent1 = new Intent(ThongtinCSYT.this, SuaCSYT.class);
            intent1.putExtra("cosoyte", csyt);

            startActivity(intent1);
        });
        btnqlyckkham.setOnClickListener(e->{
            Intent intent1 = new Intent(ThongtinCSYT.this, DSchuyenkhoaCSYT.class);
            intent1.putExtra("cosoyte", csyt);
            startActivity(intent1);
        });

    }

    private void anhxa() {
        toolbar = findViewById(R.id.tbbacsilish);
        txttenbenhvien = findViewById(R.id.txttenbenhvien);
        btnsuathongtin = findViewById(R.id.btnsuathongtin);
        btnqlyckkham = findViewById(R.id.btnqlyckkham);

    }
}