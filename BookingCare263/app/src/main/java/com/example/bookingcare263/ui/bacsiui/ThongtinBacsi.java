package com.example.bookingcare263.ui.bacsiui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.Bacsi_details;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.adapterus.adapterBaiviet;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.ui.adminui.SuaBS;
import com.example.bookingcare263.UserActivity;

import java.util.ArrayList;

public class ThongtinBacsi extends AppCompatActivity implements adapterBaiviet.setItemClick {

    private Button btnqllichhen, btnsuattbs, btnqlbaiviet;
    private TextView txttaobaiviet, txttenbs;
    RecyclerView rcvbaiviet;
    private ImageView imgavtarbsdt2, imgavatacon;
    adapterBaiviet adapterbv;
    ArrayList<Baiviet> listbaiviet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thongtin_bacsi);
        // anh xa
        anhxa();

        FirebaseHelper.getttBacsiBySdt(UserActivity.iduser, new FirebaseCallBack<Bacsi>() {
            @Override
            public void onSuccess(Bacsi data) {

                txttenbs.setText(data.getName());

                String avatarUri = data.getImg();
                if (avatarUri != null && !avatarUri.isEmpty()) {
                    Glide.with(imgavtarbsdt2.getContext())
                            .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                            .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                            .into(imgavtarbsdt2);
                } else {
                    imgavtarbsdt2.setImageResource(R.drawable.imagechose);
                }


                if (avatarUri != null && !avatarUri.isEmpty()) {
                    Glide.with(imgavatacon.getContext())
                            .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                            .circleCrop()
                            .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                            .into(imgavatacon);
                } else {
                    imgavtarbsdt2.setImageResource(R.drawable.baseline_account_circle_24);
                }


            }

            @Override
            public void onFailed(String message) {
                System.out.println("loi");

            }
        });






        btnsuattbs.setOnClickListener(e->{
            // get bacsi by sdt
            FirebaseHelper.getBacsiBySdt(UserActivity.iduser, new FirebaseCallBack<Bacsi>() {
                @Override
                public void onSuccess(Bacsi data) {

                    Intent intent = new Intent(ThongtinBacsi.this, SuaBS.class);
                    intent.putExtra("bacsi",data);
                    startActivity(intent);
                }

                @Override
                public void onFailed(String message) {
                    System.out.println("loi");

                }
            });



        });
        btnqllichhen.setOnClickListener(e->{
//            Intent intent = new Intent(ThongtinBacsi.this,QLLichHen.class);
//            startActivity(intent);
            // chuyen sang fragment lichhen


        });

        txttaobaiviet.setOnClickListener(e->{

            FirebaseHelper.getBacsiBySdt(UserActivity.iduser, new FirebaseCallBack<Bacsi>() {
                @Override
                public void onSuccess(Bacsi data) {

                    Intent intent = new Intent(ThongtinBacsi.this, Taobaiviet.class);
                    intent.putExtra("bacsi",data);
                    startActivity(intent);
                }

                @Override
                public void onFailed(String message) {
                    System.out.println("loi");

                }
            });



        });

        // capnhap bai viet
        FirebaseHelper.getbaivietbyiduser(UserActivity.iduser, new FirebaseCallBack<ArrayList<Baiviet>>() {
            @Override
            public void onSuccess(ArrayList<Baiviet> data) {
                listbaiviet.clear();
                listbaiviet.addAll(data);
                adapterbv.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String message) {

            }
        });


    }





    private void anhxa() {
        btnqllichhen = findViewById(R.id.btnqllhenn);
        btnsuattbs = findViewById(R.id.btnsuattbs);
        btnqlbaiviet = findViewById(R.id.btnqlbv);
        txttaobaiviet = findViewById(R.id.txttaobaiviet);
        rcvbaiviet = findViewById(R.id.rcvbaiviet);
        txttenbs = findViewById(R.id.txtnamebstt);
        imgavtarbsdt2 = findViewById(R.id.imgavabsttbs);
        listbaiviet = new ArrayList<>();
        imgavatacon = findViewById(R.id.imgavatacon);
        adapterbv = new adapterBaiviet(listbaiviet);
        rcvbaiviet.setLayoutManager(new LinearLayoutManager(this));
        rcvbaiviet.setAdapter(adapterbv);

    }

    @Override
    public void onItemClick(Baiviet baiviet) {

    }

    @Override
    public void onImageavatarClick(Baiviet baiviet) {

        FirebaseHelper.getBacsiBySdt(UserActivity.iduser, new FirebaseCallBack<Bacsi>() {
            @Override
            public void onSuccess(Bacsi data) {
                Intent intent = new Intent(ThongtinBacsi.this, Bacsi_details.class);
                intent.putExtra("bacsi", data);
                startActivity(intent);

            }

            @Override
            public void onFailed(String message) {
                System.out.println("loi");

            }
        });


    }

    @Override
    public void onItemDatkhamClick(Baiviet baiviet) {

    }
}