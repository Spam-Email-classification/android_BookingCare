package com.example.bookingcare263;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.Bacsi;

import java.io.File;

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
        String title = intent.getStringExtra("title");
        String anh = intent.getStringExtra("anh");
        txttiltedt.setText(title);
        txttenbsdt.setText(bacsi.getName());
        txtthogtindt.setText(bacsi.getThongtin());
        txtdiachidt.setText(bacsi.getDiachi());
        txtgiakhamdt.setText("Giá khám: " + bacsi.getGiaKham());

        Glide.with(this)
                .load(new File(this.getFilesDir(), anh))
                .into(imgavtarbsdt);

        btndatlichdt.setOnClickListener(v -> {
            if(UserActivity.iduser!=null){
                Intent intent1 = new Intent(Bacsi_details.this, Datlichkham.class);
                intent1.putExtra("bacsi", bacsi);
                intent1.putExtra("anh", anh);
                startActivity(intent1);
                finish();
            } else{
                Intent intent1 = new Intent(Bacsi_details.this, LoginActivity.class);
                startActivity(intent1);
            }
        });


    }
}