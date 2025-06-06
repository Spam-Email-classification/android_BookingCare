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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.ui.DSchuyenkhoaCSYT;
import com.example.bookingcare263.ui.uicsyt.SuaCSYT;

public class ChitietCSYT extends AppCompatActivity {

    Toolbar tbchitietcsyt;
    ImageView imgchitietcsyt;
    TextView txtHospitalName, txtAddress, tvPhone, txtemail, txtwebsite, txtsogiayphephanhnghe, tvinfo;
    Cosoyte csyt;
    Button btnviewonmap;

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
        Toast.makeText(this, "userrole" + UserActivity.roleuser, Toast.LENGTH_SHORT).show();

         if("csyt".equals(UserActivity.roleuser) || "bacsi".equals(UserActivity.roleuser))
             btnviewonmap.setVisibility(View.GONE);

        btnviewonmap.setOnClickListener(v->{
            Intent intent1 = new Intent(ChitietCSYT.this, DSchuyenkhoaCSYT.class);
            intent1.putExtra("cosoyte", csyt);

            startActivity(intent1);
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       if(roleadmin!= null )
           getMenuInflater().inflate(R.menu.menusua_xoa, menu);
       else if(csyt.getSdt().equals(UserActivity.iduser)){
           getMenuInflater().inflate(R.menu.menu_suacsyt, menu);
       }
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.csytsua) {
            Intent intent = new Intent(ChitietCSYT.this, SuaCSYT.class);
            intent.putExtra("cosoyte", csyt);
            startActivity(intent);
            finish();
            return true;
        } else if(id == R.id.csytxoa){


                FirebaseHelper.deletecsyt(csyt.getSdt());
                FirebaseHelper.deleteAccout(csyt.getSdt());
            finish();
            return true;
        }
        if(id == R.id.csytsuacsyt){
            Intent intent = new Intent(ChitietCSYT.this, SuaCSYT.class);
            intent.putExtra("cosoyte", csyt);
            startActivity(intent);
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }






    void loadData(){
        Intent intent = getIntent();
        csyt = (Cosoyte) intent.getSerializableExtra("cosoyte");


        FirebaseHelper.getcsytbyid(csyt.getSdt(), new FirebaseCallBack<Cosoyte>() {
            @Override
            public void onSuccess(Cosoyte data) {

                if(data.getImg() != null)
                    Glide.with(imgchitietcsyt.getContext()).
                            load(Uri.parse(data.getImg())).
                            into(imgchitietcsyt);
                else{
                    imgchitietcsyt.setImageResource(R.drawable.imagechose);
                }
                txtHospitalName.setText(data.getName());
                txtAddress.setText("\uD83D\uDCCD Địa chỉ :" + data.getDiachi());
                tvPhone.setText("\uD83D\uDCDE Thông tin liên hệ :"+ data.getSdt());
                txtemail.setText("Email: " + data.getEmail());
                txtwebsite.setText("Website: " + data.getWebsite());
                txtsogiayphephanhnghe.setText("Sogiayphephanhnghe: " + data.getMasogiayphep());
                tvinfo.setText(data.getThongtin());

            }

            @Override
            public void onFailed(String message) {

            }
        });
//



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

        setSupportActionBar(tbchitietcsyt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbchitietcsyt.setNavigationOnClickListener(v -> finish());

    }
}