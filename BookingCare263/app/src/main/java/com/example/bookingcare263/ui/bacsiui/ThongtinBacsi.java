package com.example.bookingcare263.ui.bacsiui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.adapterus.adapterBaiviet;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.ui.adminui.SuaBS;
import com.example.bookingcare263.ui.uiuser.UserActivity;
import com.example.bookingcare263.ui.uiuser.lichhenFragment;

import java.util.ArrayList;

public class ThongtinBacsi extends AppCompatActivity {

    private Button btnqllichhen, btnsuattbs, btnqlbaiviet;
    private TextView txttaobaiviet, txttenbs;
    RecyclerView rcvbaiviet;
    private ImageView imgavtarbsdt2;
    DatabaseHelper helper;
    adapterBaiviet adapterbv;
    ArrayList<Baiviet> listbaiviet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thongtin_bacsi);
        // anh xa
        anhxa();

        // getbac by iduserr
        Bacsi bacsi = helper.getBacsiBySdt(UserActivity.iduser);

        txttenbs.setText(bacsi.getName());

        String avatarUri = bacsi.getImg();
        if (avatarUri != null && !avatarUri.isEmpty()) {
            Glide.with(this)
                    .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgavtarbsdt2);
        } else {
            imgavtarbsdt2.setImageResource(R.drawable.baseline_account_circle_24);
        }

        btnsuattbs.setOnClickListener(e->{
            Intent intent = new Intent(ThongtinBacsi.this, SuaBS.class);
            intent.putExtra("bacsi",bacsi);
            startActivity(intent);
        });
        btnqllichhen.setOnClickListener(e->{
//            Intent intent = new Intent(ThongtinBacsi.this,QLLichHen.class);
//            startActivity(intent);
            // chuyen sang fragment lichhen


        });




        txttaobaiviet.setOnClickListener(e->{
            Intent intent = new Intent(ThongtinBacsi.this,Taobaiviet.class);
            intent.putExtra("bacsi",bacsi);
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBaiviet();

    }

    void loadBaiviet(){
        listbaiviet.clear();
        listbaiviet.addAll(helper.getbaivietbyiduser(UserActivity.iduser));
        adapterbv.notifyDataSetChanged();
    }

    private void anhxa() {
        btnqllichhen = findViewById(R.id.btnqllhenn);
        btnsuattbs = findViewById(R.id.btnsuattbs);
        btnqlbaiviet = findViewById(R.id.btnqlbv);
        txttaobaiviet = findViewById(R.id.txttaobaiviet);
        rcvbaiviet = findViewById(R.id.rcvbaiviet);
        helper = new DatabaseHelper(this);
        txttenbs = findViewById(R.id.txtnamebstt);
        imgavtarbsdt2 = findViewById(R.id.imgavabsttbs);
        listbaiviet = new ArrayList<>();

        listbaiviet.addAll(helper.getbaivietbyiduser(UserActivity.iduser));
        adapterbv = new adapterBaiviet(listbaiviet);

        rcvbaiviet.setLayoutManager(new LinearLayoutManager(this));
        rcvbaiviet.setAdapter(adapterbv);

    }

}