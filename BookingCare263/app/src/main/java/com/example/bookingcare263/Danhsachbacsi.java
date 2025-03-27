package com.example.bookingcare263;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.adapterus.adapterlistbacsi;
import com.example.bookingcare263.model.Bacsi;

import java.util.ArrayList;

public class Danhsachbacsi extends AppCompatActivity {
    private TextView txttitle;
    private TextView txtDescription;
    private TextView txtSeeMore;

    DatabaseHelper databaseHelper;

    private RecyclerView rcvlistbacsi;
    private adapterlistbacsi adapter;
    private ArrayList<Bacsi> listbacsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danhsachbacsi);

        anhxa();

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        txttitle.setText(title);
        String thongtin = intent.getStringExtra("thongtin");
        txtDescription.setText(thongtin);

        // lay du lieu tu sqlite

        databaseHelper = new DatabaseHelper(this);
        listbacsi = databaseHelper.getAllBacsi(listbacsi, title);
        adapter.notifyDataSetChanged();


        adapter.setOnClickListener(bacsi->{
            Bacsi x = bacsi;
            Intent intent1 = new Intent(Danhsachbacsi.this, Bacsi_details.class);
            intent1.putExtra("bacsi", bacsi);
            intent1.putExtra("title", title);
            intent1.putExtra("anh", bacsi.getImg());
            startActivity(intent1);

        });


    }

    private void anhxa() {
        txttitle = findViewById(R.id.txttitle);
        txtDescription = findViewById(R.id.txtDescription);
        txtSeeMore = findViewById(R.id.txtSeeMore);
        txtSeeMore.setOnClickListener(v -> {
            if (txtDescription.getMaxLines() == 2) {
                txtDescription.setMaxLines(Integer.MAX_VALUE); // Hiển thị toàn bộ nội dung
                txtSeeMore.setText("Thu gọn");
            } else {
                txtDescription.setMaxLines(2); // Giới hạn lại
                txtSeeMore.setText("Xem thêm");
            }
        });

        listbacsi = new ArrayList<>();
        rcvlistbacsi = findViewById(R.id.rcvlistbacsi);
        rcvlistbacsi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapterlistbacsi(listbacsi);
        rcvlistbacsi.setAdapter(adapter);



    }
}