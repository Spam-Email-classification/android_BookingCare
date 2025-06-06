package com.example.bookingcare263;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.ui.adminui.AdminActivity;
import com.example.bookingcare263.ui.bacsiui.ImageActivity;
import com.example.bookingcare263.ui.uiuser.Datlichkham;

public class Bacsi_details extends AppCompatActivity {
    Toolbar tbbacsidetail;
    TextView txttiltedt, txttenbsdt, txtthogtindt, txtdiachidt, txtgiakhamdt, txtemaildetail, txtsdtdetail, txtmasogiayphepsuadt;
    Button btndatlichdt;
    ImageView imgavtarbsdt, imganhgiayphepdt;

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
        txtmasogiayphepsuadt = findViewById(R.id.txtmasogiayphepsuadt);
        imganhgiayphepdt = findViewById(R.id.imganhgiayphepdt);


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
        txtemaildetail.setText("Email: " + bacsi.getEmail());
        txtsdtdetail.setText("Liên hệ: " + bacsi.getSdt());
        txtmasogiayphepsuadt.setText("Mã số giấy phép: " + bacsi.getSogiayphephanhnghe());
        txtgiakhamdt.setText("Giá khám: " + bacsi.getGiaKham());

        if("admin".equals(AdminActivity.roleadmin )|| bacsi.getSdt().equals(UserActivity.iduser)){
            imganhgiayphepdt.setVisibility(View.VISIBLE);
        } else{
            imganhgiayphepdt.setVisibility(View.GONE);
        }
        imganhgiayphepdt.setOnClickListener(e->{
            Intent intent1 = new Intent(Bacsi_details.this, ImageActivity.class);
            intent1.putExtra("anhgiayphep", bacsi.getImggiayphep());
            startActivity(intent1);

        });

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

        if( "user".equals(UserActivity.roleuser)){
            btndatlichdt.setVisibility(View.VISIBLE);
        }
         else if ( UserActivity.iduser == null && AdminActivity.roleadmin == null){
            btndatlichdt.setVisibility(View.VISIBLE);
        }
        else{
            btndatlichdt.setVisibility(View.GONE);
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