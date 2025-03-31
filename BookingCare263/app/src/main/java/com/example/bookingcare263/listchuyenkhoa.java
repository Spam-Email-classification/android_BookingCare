package com.example.bookingcare263;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.adapterus.adapterkhamchuyenkhoa;
import com.example.bookingcare263.model.Item;

import java.util.ArrayList;

public class listchuyenkhoa extends AppCompatActivity {
    private Toolbar tbkhamchuyenkhoa;
    private RecyclerView rcvkhamchuyenkhoa;
    private adapterkhamchuyenkhoa adapter;
    public  ArrayList<Item> listitems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listchuyenkhoa);

        anhxa();
        setSupportActionBar(tbkhamchuyenkhoa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbkhamchuyenkhoa.setNavigationOnClickListener(v -> onBackPressed());


    }

    private void anhxa() {


        tbkhamchuyenkhoa = findViewById(R.id.toolbar2);
        rcvkhamchuyenkhoa = findViewById(R.id.rcvkhamchuyenkhoa);
        listitems = new ArrayList<>();
        listitems.add(new Item("Cơ xương khớp", R.drawable.ck1, "Cơ xương khớp"));
        listitems.add(new Item("Thần kinh", R.drawable.ck2, "Cơ xương khớp"));
        listitems.add(new Item("Tiêu hóa", R.drawable.ck3, "Cơ xương khớp"));
        listitems.add(new Item("Tim mạch", R.drawable.ck4, "Cơ xương khớp"));
        listitems.add(new Item("Cột sống", R.drawable.ck5, "Cơ xương khớp"));
        listitems.add(new Item("Y học cổ truyền", R.drawable.ck6, "Cơ xương khớp"));
        listitems.add(new Item("Châm cứu", R.drawable.ck7, "Cơ xương khớp"));
        listitems.add(new Item("Sản phụ khoa", R.drawable.ck8, "Cơ xương khớp"));
        listitems.add(new Item("Da liễu", R.drawable.ck9, "Cơ xương khớp"));
        listitems.add(new Item("Hô hấp phổi", R.drawable.ck10, "Cơ xương khớp"));
        listitems.add(new Item("Chuyên khoa mắt", R.drawable.ck11, "Cơ xương khớp"));
        listitems.add(new Item("Thân - Tiết niệu", R.drawable.ck12, "Cơ xương khớp"));
        listitems.add(new Item("Ung bướu", R.drawable.ck13, "Cơ xương khớp"));
        listitems.add(new Item("Tư vấn, trị liệu tâm lý", R.drawable.ck14, "Cơ xương khớp"));

        adapter = new adapterkhamchuyenkhoa(listitems, R.layout.item_chuyenkhoa);
        rcvkhamchuyenkhoa.setLayoutManager(new GridLayoutManager(this, 2));

        rcvkhamchuyenkhoa.setAdapter(adapter);
        adapter.setOnItemClickListener(item -> {
            Item x = item;
            String itemName = item.getName();
            Intent intent = new Intent(this, Danhsachbacsi.class);
            intent.putExtra("title", itemName);
            intent.putExtra("thongtin", x.getThongtin());
            startActivity(intent);
            finish();

        });

    }
}