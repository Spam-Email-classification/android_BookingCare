package com.example.bookingcare263;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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
import com.example.bookingcare263.model.lichhen;

import java.util.ArrayList;

public class Danhsachbacsi extends AppCompatActivity {
    private TextView txttitle;
    private TextView txtDescription;
    private TextView txtSeeMore;
    private EditText search_bar1;

    DatabaseHelper databaseHelper;

    private RecyclerView rcvlistbacsi;
    private adapterlistbacsi adapter;
    private ArrayList<Bacsi> listbacsi;
    private ArrayList<Bacsi> filteredList;


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
        listbacsi = databaseHelper.getBacsiByChuyenKhoa(listbacsi, title);
        adapter.notifyDataSetChanged();


        adapter.setOnClickListener(bacsi->{
            Bacsi x = bacsi;
            Intent intent1 = new Intent(Danhsachbacsi.this, Bacsi_details.class);
            intent1.putExtra("bacsi", bacsi);
            intent1.putExtra("title", title);
            intent1.putExtra("anh", bacsi.getImg());
            startActivity(intent1);


        });

        search_bar1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listbacsi.clear();
                String query = search_bar1.getText().toString().trim();
                filterList(query);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private void filterList(String query) {
        filteredList.clear();
        listbacsi = databaseHelper.getAllBacsi(listbacsi);

        if (query.isEmpty()) {
            filteredList.addAll(listbacsi);  // Hiển thị tất cả nếu không nhập gì
        } else {
            for (Bacsi item : listbacsi) {
                // Kiểm tra nếu tên bác sĩ hoặc chuyên khoa chứa từ khóa tìm kiếm
                if (item.getName().toLowerCase().contains(query.toLowerCase()) ||
                        item.getChuyenkhoa().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        adapter.updateList(filteredList);

    }



    private void anhxa() {
        filteredList = new ArrayList<>();

        search_bar1 = findViewById(R.id.search_bar1);
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