package com.example.bookingcare263;

import static com.example.bookingcare263.ui.adminui.AdminActivity.roleadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.adapterus.adapterkhamchuyenkhoa;
import com.example.bookingcare263.model.chuyenkhoa;
import com.example.bookingcare263.ui.adminui.SuaChuyenKhoa;
import com.example.bookingcare263.ui.adminui.ThemChuyenkhoa;
import com.example.bookingcare263.ui.adminui.adDanhsachBs;
import com.example.bookingcare263.ui.uiuser.Danhsachbacsi;

import java.util.ArrayList;

public class listchuyenkhoa extends AppCompatActivity {
    private Toolbar tbkhamchuyenkhoa;
    private RecyclerView rcvkhamchuyenkhoa;
    private adapterkhamchuyenkhoa adapter;
    public  ArrayList<chuyenkhoa> listitems;
    DatabaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listchuyenkhoa);

        anhxa();

        // toolbar

        setSupportActionBar(tbkhamchuyenkhoa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbkhamchuyenkhoa.setNavigationOnClickListener(v -> onBackPressed());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (roleadmin == "admin") {
            getMenuInflater().inflate(R.menu.addmenu, menu);
        }
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.plusss) {
            // add
            Intent intent1 = new Intent(listchuyenkhoa.this, ThemChuyenkhoa.class);
            startActivity(intent1);


        }


        return super.onOptionsItemSelected(item);
    }




    private void anhxa() {


        tbkhamchuyenkhoa = findViewById(R.id.toolbar2);
        rcvkhamchuyenkhoa = findViewById(R.id.rcvkhamchuyenkhoa);
        listitems = new ArrayList<>();
        helper = new DatabaseHelper(this);
        adapter = new adapterkhamchuyenkhoa(listitems, R.layout.item_chuyenkhoa);
        rcvkhamchuyenkhoa.setLayoutManager(new GridLayoutManager(this, 2));
        rcvkhamchuyenkhoa.setAdapter(adapter);
        adapter.setOnItemClickListener(item -> {

            chuyenkhoa x = item;

            if(roleadmin == null) {

                String itemName = item.getTenchuyenkhoa();
                Intent intent = new Intent(this, Danhsachbacsi.class);
                intent.putExtra("title", itemName);
                intent.putExtra("thongtin", x.getThongtin());
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, SuaChuyenKhoa.class);
                intent.putExtra("chuyen khoa", x);
                startActivity(intent);

            }
        });

        FirebaseHelper.getChuyenKhoa(new FirebaseCallBack<ArrayList<chuyenkhoa>>() {
            @Override
            public void onSuccess(ArrayList<chuyenkhoa> data) {
                listitems.clear();
                listitems.addAll(data);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String message) {

            }
        });

    }
}