package com.example.bookingcare263.ui.adminui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

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
import com.example.bookingcare263.adapterus.adapterbsAd;
import com.example.bookingcare263.adapterus.adaptercosoyte;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.ui.uiuser.UserActivity;

import java.util.ArrayList;

public class adDanhsachBs extends AppCompatActivity implements adapterbsAd.onClickListener {


    Toolbar toolbar;
    RecyclerView rcvlisbsad;
    adapterbsAd adapter;
    ArrayList<Bacsi> listbacsi;
    ArrayList<Cosoyte> listcsyte;

    adaptercosoyte adaptercsyt;

    EditText txtsearch;
    DatabaseHelper helper;

    String manager;

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
            listbacsi = new ArrayList<>();
            listbacsi = helper.getAllBacsi(listbacsi);
            adapter = new adapterbsAd(listbacsi);
            rcvlisbsad.setAdapter(adapter);
            rcvlisbsad.setLayoutManager(new LinearLayoutManager(this));
            adapter.setOnClickListener(this);

        } else if(manager != null && manager.equals("quanlybenhnhan")){
            toolbar.setTitle("Quản lý bệnh nhân");


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
        listbacsi.clear();
        listbacsi = helper.getAllBacsi(listbacsi);
        adapter.notifyDataSetChanged();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addmenu, menu);
        return true;
    }

    @Override
    public void onItemClick(Bacsi bacsi) {
//        Intent intent = new Intent(this, Bacsi_details.class);
//        intent.putExtra("bacsi", bacsi);
//        intent.putExtra("anh", bacsi.getImg());
//        intent.putExtra("title", bacsi.getChuyenkhoa());
//        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Bacsi bacsi) {
        helper.deleteBacsi(bacsi.getId());
        listbacsi.remove(bacsi);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void onFixClick(Bacsi bacsi) {
        Intent intent = new Intent(this, SuaBS.class);
        intent.putExtra("bacsi", bacsi);
        startActivity(intent);


    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.plusss) {
            if (manager != null && manager.equals("quanlybacsi")) {
                Intent intent = new Intent(adDanhsachBs.this, AddBacSi.class);
                startActivity(intent);
            } else if(manager != null && manager.equals("quanlycsyt")){
                Intent intent = new Intent(adDanhsachBs.this, AddCSYT.class);
                startActivity(intent);
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}