package com.example.bookingcare263.ui.adminui;

import static com.example.bookingcare263.ui.adminui.AdminActivity.roleadmin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.Bacsi_details;
import com.example.bookingcare263.ChitietCSYT;
import com.example.bookingcare263.FCMHelper;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.adapterus.adapterAccout;
import com.example.bookingcare263.adapterus.adapterBaiviet;
import com.example.bookingcare263.adapterus.adaptercosoyte;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.accout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adDanhsachBs extends AppCompatActivity implements adapterAccout.onClickListener {


    Toolbar toolbar;
    RecyclerView rcvlisbsad;

    ArrayList<Cosoyte> listcsyte;

    adaptercosoyte adaptercsyt;

    EditText edtsearch;


    String manager;

    adapterAccout adapteracc;
    ArrayList <accout> listacc;
    ArrayList <Baiviet> listbaiviet;
    adapterBaiviet adapter;
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
            listacc = new ArrayList<>();
            adapteracc = new adapterAccout(listacc);
            rcvlisbsad.setAdapter(adapteracc);
            rcvlisbsad.setLayoutManager(new LinearLayoutManager(this));
            adapteracc.setonListener(this);
            edtsearch.setHint("Tìm tên hoặc số điện thoại bác sĩ");

            // get list acc by  role = bac si

            FirebaseHelper.getaccoutbyrole("bacsi", new FirebaseCallBack<ArrayList<accout>>() {
                @Override
                public void onSuccess(ArrayList<accout> data) {
                    listacc.clear();
                    listacc.addAll(data);
                    adapteracc.notifyDataSetChanged();
                }

                @Override
                public void onFailed(String message) {

                }
            });




        } else if(manager != null && manager.equals("quanlybenhnhan")){
            toolbar.setTitle("Quản lý bệnh nhân");
            listacc = new ArrayList<>();
            adapteracc = new adapterAccout(listacc);
            rcvlisbsad.setAdapter(adapteracc);
            rcvlisbsad.setLayoutManager(new LinearLayoutManager(this));
            adapteracc.setonListener(this);
            edtsearch.setHint("Tìm tên hoặc số điện thoại bệnh nhân");

            FirebaseHelper.getaccoutbyrole("user", new FirebaseCallBack<ArrayList<accout>>() {
                @Override
                public void onSuccess(ArrayList<accout> data) {
                    listacc.clear();
                    listacc.addAll(data);
                    adapteracc.notifyDataSetChanged();

                }

                @Override
                public void onFailed(String message) {

                }
            });
            // size list



        } else if (manager != null && manager.equals("quanlycsyt")) {
            toolbar.setTitle("QUẢN LÝ CSYT");
            listcsyte = new ArrayList<>();

            adaptercsyt = new adaptercosoyte(listcsyte);

            rcvlisbsad.setAdapter(adaptercsyt);
            rcvlisbsad.setLayoutManager(new GridLayoutManager(this, 2));
            adaptercsyt.setOnItemClickListener(csyt->{
                Intent intent1 = new Intent(adDanhsachBs.this, ChitietCSYT.class);
                intent1.putExtra("cosoyte", csyt);
                startActivity(intent1);
            });

            FirebaseHelper.getcosoyte(new FirebaseCallBack<ArrayList<Cosoyte>>() {
                @Override
                public void onSuccess(ArrayList<Cosoyte> data) {
                    listcsyte.clear();
                    listcsyte.addAll(data);
                    adaptercsyt.notifyDataSetChanged();
                }

                @Override
                public void onFailed(String message) {

                }
            });


            edtsearch.setHint("Tìm cơ sở y tế");
        } else if(manager != null && manager.equals("quanlybv")){
            edtsearch.setHint("Tìm tên bác sĩ");
            toolbar.setTitle("Quản lý bài viết");
            listbaiviet = new ArrayList<>();
            adapter = new adapterBaiviet(listbaiviet);
            rcvlisbsad.setAdapter(adapter);
            rcvlisbsad.setLayoutManager(new LinearLayoutManager(this));

            FirebaseHelper.getAllbaiviet(new FirebaseCallBack<ArrayList<Baiviet>>() {
                @Override
                public void onSuccess(ArrayList<Baiviet> data) {
                    listbaiviet.clear();
                    listbaiviet.addAll(data);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailed(String message) {

                }
            });
            adapter.setOnItemClickListener(new adapterBaiviet.setItemClick() {
                @Override
                public void onItemClick(Baiviet baiviet) {

                }

                @Override
                public void onImageavatarClick(Baiviet baiviet) {

                }

                @Override
                public void onItemDatkhamClick(Baiviet baiviet) {

                }

                @Override
                public void onItemDeleteClick(Baiviet baiviet) {
                    FirebaseHelper.deletebaiviet(baiviet.getId());

                }
            });


        }

        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                listbacsi.clear();
                String query = edtsearch.getText().toString().trim();
//                filterList(query);

                if (manager != null && manager.equals("quanlybacsi")) {

                    filterList(query, "bacsi");
                } else if (manager != null && manager.equals("quanlybenhnhan")) {
                    filterList(query, "user");
                } else if (manager != null && manager.equals("quanlycsyt")) {
                    filterListcsyt(query);
                }
                else if(manager != null && manager.equals("quanlybv")){
                    // get bai viet by id bacsi



                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //
    private void filterList(String query, String role) {

        ArrayList<accout> newlist = new ArrayList();
        listacc.clear();

        FirebaseHelper.getaccoutbyrole(role, new FirebaseCallBack<ArrayList<accout>>() {
            @Override
            public void onSuccess(ArrayList<accout> data) {
                listacc.clear();
                listacc.addAll(data);


                newlist.clear();
                if(query.isEmpty()){
                    newlist.addAll(listacc);
                } else{
                    for (accout acc : listacc) {
                        if (acc.getName().toLowerCase().contains(query.toLowerCase()) ||
                                acc.getPhone().toLowerCase().contains(query.toLowerCase())) {
                            newlist.add(acc);
                        }}
                }
                listacc.clear();
                listacc.addAll(newlist);
                adapteracc.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String message) {

            }
        });

    }



    private void filterListcsyt(String query) {
        ArrayList<Cosoyte> newlist = new ArrayList<>();
        listcsyte.clear();


        FirebaseHelper.getcosoyte(new FirebaseCallBack<ArrayList<Cosoyte>>() {
            @Override
            public void onSuccess(ArrayList<Cosoyte> data) {
                listcsyte.clear();
                listcsyte.addAll(data);

                if( query.isEmpty()){
                    newlist.addAll(listcsyte);
                }
                else{
                    for (Cosoyte csyt : listcsyte) {
                        if (csyt.getName().toLowerCase().contains(query.toLowerCase())) {
                            newlist.add(csyt);
                        }}
                }
                listcsyte.clear();
                listcsyte.addAll(newlist);
                adaptercsyt.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String message) {

            }
        });
    }





    private void anhxa() {
        toolbar = findViewById(R.id.tbqlbsad);
        rcvlisbsad = findViewById(R.id.rcvbsad);
        edtsearch = findViewById(R.id.edtsearchad);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

    }

    @Override
    public void onStatusLongClick(int position) {
        accout acc = listacc.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Trạng thái tài khoản")
                .setItems(new String[]{"Đang hoạt động", "Chờ duyệt", "Tạm khóa"}, (dialog, which) -> {
                    String newStatus;
                    if (which == 0) {
                        newStatus = "Đang hoạt động";
                    } else if (which == 1) {
                        newStatus = "Chờ duyệt";
                    } else {
                        newStatus = "Tạm khóa";
                    }

                    // Cập nhật trạng thái
                    acc.setStatus(newStatus);

                    FCMHelper.sendNotification(adDanhsachBs.this, acc.getToken(), "Trạng thái tài khoản đã ", newStatus);

                    adapteracc.notifyDataSetChanged(); // Cập nhật lại RecyclerView


                    // update len firebase
                    reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(acc.getPhone()).child("status").setValue(newStatus);

                })
                .show();
    }






    @Override
    public void onItemClick(int position) {
        // get bacsi by sdt
        if (manager != null && manager.equals("quanlybacsi")) {
            String sdt = listacc.get(position).getPhone();


            // Lấy bác sĩ theo số điện thoại để chuyển đến trang sửa
            FirebaseHelper.getBacsiBySdt(sdt, new FirebaseCallBack<Bacsi>() {
                @Override
                public void onSuccess(Bacsi data) {
                    // Lưu đối tượng bacsi đã lấy được từ Firebase

                    if(data == null){
                        Toast.makeText(adDanhsachBs.this, "Không tìm thấy bác sĩ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(adDanhsachBs.this, Bacsi_details.class);
                    intent.putExtra("bacsi", data);  // Truyền đối tượng bacsi vào Intent
                    startActivity(intent);
                }

                @Override
                public void onFailed(String message) {
                    // Xử lý lỗi khi không lấy được bác sĩ từ Firebase
                    Log.e("Firebase", "Lỗi: " + message);
                }
            });
        }


       else if(manager != null && manager.equals("quanlybenhnhan")){
           Toast.makeText(this, "Bạn không có quyền xem tài khoản này", Toast.LENGTH_SHORT).show();

        }

    }




}