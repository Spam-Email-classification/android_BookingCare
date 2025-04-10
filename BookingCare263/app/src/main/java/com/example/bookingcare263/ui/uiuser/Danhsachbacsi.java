package com.example.bookingcare263.ui.uiuser;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.Bacsi_details;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.adapterus.adapterlistbacsi;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.accout;

import java.util.ArrayList;

public class Danhsachbacsi extends AppCompatActivity {
    Toolbar tbbacsi;
    private TextView txttitle;
    private TextView txtDescription;
    private TextView txtSeeMore;
    private EditText search_bar1;


    private RecyclerView rcvlistbacsi;
    private adapterlistbacsi adapter;
    private ArrayList<Bacsi> listbacsi;
    private ArrayList<Bacsi> filteredList;
    private ArrayList<accout> listaccout;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danhsachbacsi);

        anhxa();

        Intent intent = getIntent();
         title = intent.getStringExtra("title");
        txttitle.setText(title);
        String thongtin = intent.getStringExtra("thongtin");
        txtDescription.setText(thongtin);

        // lay du lieu tu sqlite

        if(title.equals("ĐẶT LỊCH KHÁM")){

            FirebaseHelper.getaccoutbyStatusAndRole( "Đang hoạt động", "bacsi", new FirebaseCallBack<ArrayList<accout>>()
            {
                @Override
                public void onSuccess(ArrayList<accout> data) {
                    listaccout.clear();
                    listaccout.addAll(data);


                    // get listbac bysdt
                    listbacsi.clear();
                    for (accout accout : listaccout) {
                        FirebaseHelper.getBacsiBySdt(accout.getPhone(), new FirebaseCallBack<Bacsi>(){
                            @Override
                            public void onSuccess(Bacsi data) {
                                listbacsi.add(data);

                                if(listbacsi.size() == listaccout.size()){
                                    adapter.updateList(new ArrayList<>(listbacsi));
                                }

                            }
                            @Override
                            public void onFailed(String message) {
                                Toast.makeText(Danhsachbacsi.this, message, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                @Override
                public void onFailed(String message) {
                    Toast.makeText(Danhsachbacsi.this, message, Toast.LENGTH_SHORT).show();
                }
            });




        }
        else {


            FirebaseHelper.getaccoutbyStatusAndRole("Đang hoạt động", "bacsi", new FirebaseCallBack<ArrayList<accout>>() {
                @Override
                public void onSuccess(ArrayList<accout> data) {
                    listaccout.clear();
                    listaccout.addAll(data);

                    // get listbac bysdt
                    listbacsi.clear();
                    for (accout accout : listaccout) {
                        FirebaseHelper.getBacsiByChuyenkhoaAndSdt(title, accout.getPhone(), new FirebaseCallBack<Bacsi>() {
                            @Override
                            public void onSuccess(Bacsi data) {
                                listbacsi.add(data);

                                    adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailed(String message) {

                            }
                        });
                    }

                }

                @Override
                public void onFailed(String message) {
                    Toast.makeText(Danhsachbacsi.this, message, Toast.LENGTH_SHORT).show();
                }
            });


        }
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
                String query = search_bar1.getText().toString().trim();
                filterList(query);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private void filterList(String query) {
        ArrayList<accout> listacc = new ArrayList<>();




        FirebaseHelper.getaccoutbyStatusAndRoletinh("Đang hoạt động", "bacsi", new FirebaseCallBack<ArrayList<accout>>() {
            @Override
            public void onSuccess(ArrayList<accout> listAcc) {
                listacc.clear();
                listacc.addAll(listAcc);

                // Bước 2 sẽ làm trong này
                Log.d("TAG", "Lấy được acc: " + listAcc.size());

                listbacsi.clear();
                int[] count = {0};

                Log.d("title", title + "");

                if(title.equals("ĐẶT LỊCH KHÁM") || title.equals("Tìm kiếm bác sĩ, chuyên khoa khám")){
                    listbacsi.clear();
                    for (accout acc : listAcc) {
                        FirebaseHelper.getBacsiBySdt(acc.getPhone(), new FirebaseCallBack<Bacsi>() {
                            @Override
                            public void onSuccess(Bacsi data) {
                                listbacsi.add(data);
                                count[0]++;
                                if (count[0] == listAcc.size()) {
                                    applyFilter(query); // Gọi lọc ban đầu không có query
                                }

                            }

                            @Override
                            public void onFailed(String message) {
                                count[0]++;
                                if (count[0] == listAcc.size()) {
                                    applyFilter(query); // Gọi lọc nếu acc cuối cùng bị fail
                                }

                            }
                        });
                    }
                }
                else {
                    listbacsi.clear();
                    for (accout acc : listAcc) {
                        Log.d("TAG", "acc phone: " + acc.getPhone());
                        FirebaseHelper.getBacsiByChuyenkhoaAndSdt(title, acc.getPhone(), new FirebaseCallBack<Bacsi>() {
                            @Override
                            public void onSuccess(Bacsi bacsi) {
                                if (bacsi != null) listbacsi.add(bacsi);
                                count[0]++;
                                if (count[0] == listAcc.size()) {

                                    applyFilter(query); // Gọi lọc ban đầu không có query
                                }
                            }

                            @Override
                            public void onFailed(String message) {
                                count[0]++;
                                if (count[0] == listAcc.size()) {
                                    applyFilter(query); // Gọi lọc nếu acc cuối cùng bị fail
                                }
                            }
                        });
                    }

                }

            }

            @Override
            public void onFailed(String message) {
                Log.e("TAG", "Không lấy được acc: " + message);
            }
        });

    }

    private void applyFilter(String query) {
        filteredList.clear();
        Log.d("size list bacsi", "" + listbacsi.size());
        if (query.isEmpty()) {
            filteredList.addAll(listbacsi);
        } else {
            for (Bacsi b : listbacsi) {
                if (b.getName().toLowerCase().contains(query.toLowerCase()) ||
                        b.getChuyenkhoa().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(b);
                }
            }
        }
        adapter.updateList(filteredList); // Cập nhật hiển thị danh sách
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
        tbbacsi = findViewById(R.id.tbbacsilish);
        listaccout = new ArrayList<>();


        // tool bar
        setSupportActionBar(tbbacsi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbbacsi.setNavigationOnClickListener(v -> finish());


        listbacsi = new ArrayList<>();
        rcvlistbacsi = findViewById(R.id.rcvlistbacsi);
        rcvlistbacsi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapterlistbacsi(listbacsi);
        rcvlistbacsi.setAdapter(adapter);



    }
}