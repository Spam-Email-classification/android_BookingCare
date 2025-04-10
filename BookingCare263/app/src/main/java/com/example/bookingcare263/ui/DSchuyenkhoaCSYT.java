package com.example.bookingcare263.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.LoginActivity;
import com.example.bookingcare263.R;
import com.example.bookingcare263.UserActivity;
import com.example.bookingcare263.adapterus.adapterckcsyt;
import com.example.bookingcare263.model.Chuyenkhoacsyt;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.chuyenkhoa;
import com.example.bookingcare263.ui.uicsyt.Addckcsyt;
import com.example.bookingcare263.ui.uiuser.Datlichkham;

import java.util.ArrayList;

public class DSchuyenkhoaCSYT extends AppCompatActivity implements  adapterckcsyt.OnClickListener{
    Toolbar toolbar;
    RecyclerView rvdschuyenkhoacsyt;
    adapterckcsyt adapter;
    ArrayList<Chuyenkhoacsyt> listckcsyt;
    Cosoyte csyt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dschuyenkhoa_csyt);
        anhxa();
        Intent intent = getIntent();
        csyt = (Cosoyte) intent.getSerializableExtra("cosoyte");

        // getchuykhoacsyt by sdtcsyt

        FirebaseHelper.getchuyenkhoaCSYTbyid(csyt.getSdt(), new FirebaseCallBack<ArrayList<Chuyenkhoacsyt>>() {
            @Override
            public void onSuccess(ArrayList<Chuyenkhoacsyt> data) {
                listckcsyt.clear();
                listckcsyt.addAll(data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String message) {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.addmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.plusss) {
            // add
            Intent intent1 = new Intent(DSchuyenkhoaCSYT.this, Addckcsyt.class);
            intent1.putExtra("cosoyte", csyt);
            startActivity(intent1);


        }


        return super.onOptionsItemSelected(item);
    }

    private void anhxa() {
        toolbar = findViewById(R.id.tbdsckcsyt);
        rvdschuyenkhoacsyt = findViewById(R.id.rvdschuyenkhoacsyt);
        listckcsyt = new ArrayList<>();
        adapter = new adapterckcsyt(listckcsyt);
        rvdschuyenkhoacsyt.setAdapter(adapter);
        rvdschuyenkhoacsyt.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });


    }

    @Override
    public void onItemClick(Chuyenkhoacsyt items) {

    }

    @Override
    public void onItemdatkham(Chuyenkhoacsyt items) {
        if(UserActivity.iduser == null){
            Intent intent = new Intent(DSchuyenkhoaCSYT.this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        Intent intent = new Intent(DSchuyenkhoaCSYT.this, Datlichkham.class);
        intent.putExtra("chuyenkhoa", items);
        startActivity(intent);

    }

    @Override
    public void onItemDelete(Chuyenkhoacsyt items) {

    }
}