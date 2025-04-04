package com.example.bookingcare263.ui.adminui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.Bacsi_details;
import com.example.bookingcare263.ChitietCSYT;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.LoginActivity;
import com.example.bookingcare263.R;
import com.example.bookingcare263.ThongtinUser;
import com.example.bookingcare263.adapterus.adapterAccout;
import com.example.bookingcare263.adapterus.adapterbsAd;
import com.example.bookingcare263.adapterus.adaptercosoyte;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.ui.uiuser.UserActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adDanhsachBs extends AppCompatActivity implements adapterAccout.onClickListener {


    Toolbar toolbar;
    RecyclerView rcvlisbsad;

    ArrayList<Cosoyte> listcsyte;

    adaptercosoyte adaptercsyt;

    EditText txtsearch;
    DatabaseHelper helper;

    String manager;

    adapterAccout adapteracc;
    ArrayList <accout> listacc;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ad_danhsach_bs);

        anhxa();
        Intent intent = getIntent();
        manager = intent.getStringExtra("manager");
        if (manager != null && manager.equals("quanlybacsi")) {
            toolbar.setTitle("Quản lý bác sĩ");
            // get list acc by  role = bac si
            listacc = new ArrayList<>();
            listacc = helper.getaccoutbyrole("bacsi");
            adapteracc = new adapterAccout(listacc);
            rcvlisbsad.setAdapter(adapteracc);
            rcvlisbsad.setLayoutManager(new LinearLayoutManager(this));
            adapteracc.setonListener(this);




        } else if(manager != null && manager.equals("quanlybenhnhan")){
            toolbar.setTitle("Quản lý bệnh nhân");
            listacc = new ArrayList<>();
            listacc = helper.getaccoutbyrole("benh nhan");
            adapteracc = new adapterAccout(listacc);
            rcvlisbsad.setAdapter(adapteracc);
            rcvlisbsad.setLayoutManager(new LinearLayoutManager(this));
            adapteracc.setonListener(this);


        } else if (manager != null && manager.equals("quanlycsyt")) {
            toolbar.setTitle("QUẢN LÝ CSYT");
            listcsyte = new ArrayList<>();
            listcsyte = helper.getCosoyte();
            Log.d("listcsyt", listcsyte.size( ) +"");
            adaptercsyt = new adaptercosoyte(listcsyte);

            rcvlisbsad.setAdapter(adaptercsyt);
            rcvlisbsad.setLayoutManager(new GridLayoutManager(this, 2));
            adaptercsyt.setOnItemClickListener(csyt->{
                Intent intent1 = new Intent(adDanhsachBs.this, ChitietCSYT.class);
                intent1.putExtra("cosoyte", csyt);
                startActivity(intent1);
            });
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(manager != null && manager.equals("quanlybacsi")){
            loadDatabs();
        }
        if(manager != null && manager.equals("quanlycsyt")){
            loadDatacsyt();
        }

    }

    public void loadDatacsyt(){
        listcsyte.clear();
        listcsyte.addAll(helper.getCosoyte());
        adaptercsyt.notifyDataSetChanged();

    }

    public void loadDatabs(){
        listacc.clear();
        listacc.addAll(helper.getaccoutbyrole("bacsi"));
        adapteracc.notifyDataSetChanged();

    }

    public void loadDatabenhnha(){
        listacc.clear();
        listacc.addAll(helper.getaccoutbyrole("benh nhan"));
        adapteracc.notifyDataSetChanged();

    }


    private void anhxa() {
        toolbar = findViewById(R.id.tbqlbsad);
        rcvlisbsad = findViewById(R.id.rcvbsad);
        txtsearch = findViewById(R.id.edtsearchad);
        helper = new DatabaseHelper(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());



    }


    @Override
    public void onFixClick(int position) {
        String sdt = listacc.get(position).getPhone();
        Bacsi bacsi = helper.getBacsiBySdt(sdt);
        Intent intent = new Intent(adDanhsachBs.this, SuaBS.class);
        intent.putExtra("bacsi", bacsi);
        startActivity(intent);

    }

    // xoa accout tren firebase


    @Override
    public void onDeleteClick(int position) {
        String sdt = listacc.get(position).getPhone();
        String role = listacc.get(position).getAs();
        helper.deleteaccout(sdt);
        if (role.equals("bacsi")){
            helper.deleteBacsi(sdt);
            loadDatabs();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            // xoa tai khoan tren firebase realtime

        } else {
            helper.deletebenhnhan(sdt);
            loadDatabenhnha();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
        reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.child(sdt).removeValue();



    }

    @Override
    public void onStatusLongClick(int position) {

    }

    @Override
    public void onItemClick(int position) {
        // get bacsi by sdt
        String sdt = listacc.get(position).getPhone();
        Bacsi bacsi = helper.getBacsiBySdt(sdt);
        Intent intent = new Intent(adDanhsachBs.this, Bacsi_details.class);
        intent.putExtra("bacsi",bacsi);
        startActivity(intent);

    }
}