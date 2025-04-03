package com.example.bookingcare263;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.ui.uiuser.Datlichkham;
import com.example.bookingcare263.ui.uiuser.UserActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Bacsi_details extends AppCompatActivity {
    TextView txttiltedt, txttenbsdt, txtthogtindt, txtdiachidt, txtgiakhamdt;
    Button btndatlichdt;
    ImageView imgavtarbsdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bacsi_details);
        anhxa();

        loadData();


    }

    private void anhxa() {
        txttiltedt = findViewById(R.id.txttiltedt);
        txttenbsdt = findViewById(R.id.txttenbsdt);
        txtthogtindt = findViewById(R.id.txtthogtindt);
        txtdiachidt = findViewById(R.id.txtdiachidt);
        txtgiakhamdt = findViewById(R.id.txtgiakhamdt);
        btndatlichdt = findViewById(R.id.btndatlichdt);
        imgavtarbsdt = findViewById(R.id.imgavtarbsdt2);
    }

    private void loadData(){
        Intent intent = getIntent();
        Bacsi bacsi = (Bacsi) intent.getSerializableExtra("bacsi");
        txttiltedt.setText(bacsi.getChuyenkhoa());
        txttenbsdt.setText(bacsi.getName());
        txtthogtindt.setText(bacsi.getThongtin());
        txtdiachidt.setText(bacsi.getDiachi());
        txtgiakhamdt.setText("Giá khám: " + bacsi.getGiaKham());

        String avatarUri = bacsi.getImg();
        Glide.with(imgavtarbsdt.getContext())
                .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                .into(imgavtarbsdt);


        btndatlichdt.setOnClickListener(v -> {
            if(UserActivity.iduser!=null){
                Intent intent1 = new Intent(Bacsi_details.this, Datlichkham.class);
                intent1.putExtra("bacsi", bacsi);
                intent1.putExtra("anh", bacsi.getImg());
                startActivity(intent1);
                finish();
            } else{
                Intent intent1 = new Intent(Bacsi_details.this, LoginActivity.class);
                startActivity(intent1);
            }
        });


    }
}