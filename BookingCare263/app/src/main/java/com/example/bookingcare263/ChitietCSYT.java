package com.example.bookingcare263;

import static com.example.bookingcare263.ui.adminui.AdminActivity.roleadmin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.ui.adminui.SuaCSYT;

public class ChitietCSYT extends AppCompatActivity {

    Toolbar tbchitietcsyt;
    ImageView imgchitietcsyt;
    TextView txtHospitalName, txtAddress, tvPhone, txtemail, txtwebsite, txtsogiayphephanhnghe, tvinfo;
    Cosoyte csyt;
    Button btnviewonmap;
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chitiet_csyt);
        anhxa();
        loadData();
        if(roleadmin!= null){
          btnviewonmap.setVisibility(View.GONE);
        }
        Intent intent = getIntent();
         csyt = (Cosoyte) intent.getSerializableExtra("cosoyte");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       if(roleadmin!= null)
           getMenuInflater().inflate(R.menu.menusua_xoa, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.csytsua) {
            Intent intent = new Intent(ChitietCSYT.this, SuaCSYT.class);
            intent.putExtra("cosoyte", csyt);
            startActivity(intent);
            return true;
        }
        if (id == R.id.csytxoa) {
            helper.deletecsyt(csyt.getId());
            finish();

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    void loadData(){
        Intent intent = getIntent();
        csyt = (Cosoyte) intent.getSerializableExtra("cosoyte");

        Cosoyte  csyt1 = helper.getCosoyteById(csyt.getId());
        Glide.with(this).
                load(Uri.parse(csyt1.getImg())).
                into(imgchitietcsyt);

        txtHospitalName.setText(csyt1.getName());
        txtAddress.setText("\uD83D\uDCCD Địa chỉ :" + csyt1.getDiachi());
        tvPhone.setText("\uD83D\uDCDE Thông tin liên hệ :"+ csyt1.getSdt());
        txtemail.setText("Email: " + csyt1.getEmail());
        txtwebsite.setText("Website: " + csyt1.getWebsite());
        txtsogiayphephanhnghe.setText("Sogiayphephanhnghe: " + csyt1.getMasogiayphep());
        tvinfo.setText(csyt1.getThongtin());


    }

    private void anhxa() {
        tbchitietcsyt = findViewById(R.id.tbchitietbenhvien);
        imgchitietcsyt = findViewById(R.id.imgHospital);
        txtHospitalName = findViewById(R.id.tvHospitalName);
        txtAddress = findViewById(R.id.tvAddress);
        tvPhone = findViewById(R.id.tvPhone);
        txtemail = findViewById(R.id.txtemail);
        txtwebsite = findViewById(R.id.txtwebsite);
        txtsogiayphephanhnghe = findViewById(R.id.txtsogiayphephanhnghe);
        tvinfo = findViewById(R.id.tvInfo);
        btnviewonmap = findViewById(R.id.btnViewOnMap);
        helper = new DatabaseHelper(this);

        setSupportActionBar(tbchitietcsyt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbchitietcsyt.setNavigationOnClickListener(v -> finish());

    }
}