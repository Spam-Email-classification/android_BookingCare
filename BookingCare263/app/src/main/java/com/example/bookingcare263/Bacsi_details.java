package com.example.bookingcare263;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.ui.uiuser.Datlichkham;

public class Bacsi_details extends AppCompatActivity {
    Toolbar tbbacsidetail;
    TextView txttiltedt, txttenbsdt, txtthogtindt, txtdiachidt, txtgiakhamdt, txtemaildetail, txtsdtdetail;
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
        txtemaildetail = findViewById(R.id.txtemaildetail);
        txtsdtdetail = findViewById(R.id.txtsdtdetail);
        tbbacsidetail = findViewById(R.id.tbbacsidetail);


        // toolbar
        setSupportActionBar(tbbacsidetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbbacsidetail.setNavigationOnClickListener(v -> finish());
    }

    private void loadData(){
        Intent intent = getIntent();
        Bacsi bacsi = (Bacsi) intent.getSerializableExtra("bacsi");

        txttiltedt.setText(bacsi.getChuyenkhoa());
        txttenbsdt.setText(bacsi.getName());
        txtthogtindt.setText(bacsi.getThongtin());
        txtdiachidt.setText(bacsi.getDiachi());
        txtemaildetail.setText("Email" + bacsi.getEmail());
        txtsdtdetail.setText("Liên hệ: " + bacsi.getSdt());

        txtgiakhamdt.setText("Giá khám: " + bacsi.getGiaKham());

        String avatarUri = bacsi.getImg();
        if (avatarUri != null && !avatarUri.isEmpty()) {

            Glide.with(imgavtarbsdt.getContext())
                    .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgavtarbsdt);
        } else{
            imgavtarbsdt.setImageResource(R.drawable.imagechose);
        }

        btndatlichdt.setOnClickListener(v -> {
            if(UserActivity.iduser!=null){
                Intent intent1 = new Intent(Bacsi_details.this, Datlichkham.class);
                intent1.putExtra("bacsi", bacsi);
                startActivity(intent1);
                finish();
            } else{
                Intent intent1 = new Intent(Bacsi_details.this, LoginActivity.class);
                startActivity(intent1);
            }
        });


    }
}