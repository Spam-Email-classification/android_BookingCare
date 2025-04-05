package com.example.bookingcare263.ui.adminui;

import static com.example.bookingcare263.ui.adminui.AdminActivity.roleadmin;
import static com.example.bookingcare263.ui.uiuser.UserActivity.listaccactive;

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
import com.example.bookingcare263.adapterus.adapterAccout;
import com.example.bookingcare263.adapterus.adapterbsAd;
import com.example.bookingcare263.adapterus.adaptercosoyte;
import com.example.bookingcare263.listchuyenkhoa;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.ui.uiuser.UserActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adDanhsachBs extends AppCompatActivity implements adapterAccout.onClickListener {


    Toolbar toolbar;
    RecyclerView rcvlisbsad;

    ArrayList<Cosoyte> listcsyte;

    adaptercosoyte adaptercsyt;

    EditText edtsearch;
    DatabaseHelper helper;

    String manager;

    adapterAccout adapteracc;
    ArrayList <accout> listacc;
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
            // get list acc by  role = bac si
            listacc = new ArrayList<>();
            listacc = helper.getaccoutbyrole("bacsi");
            adapteracc = new adapterAccout(listacc);
            rcvlisbsad.setAdapter(adapteracc);
            rcvlisbsad.setLayoutManager(new LinearLayoutManager(this));
            adapteracc.setonListener(this);
            edtsearch.setText("Tìm tên hoặc số điện thoại bác sĩ");
            // size list




        } else if(manager != null && manager.equals("quanlybenhnhan")){
            toolbar.setTitle("Quản lý bệnh nhân");
            listacc = new ArrayList<>();
            listacc = helper.getaccoutbyrole("benh nhan");
            adapteracc = new adapterAccout(listacc);
            rcvlisbsad.setAdapter(adapteracc);
            rcvlisbsad.setLayoutManager(new LinearLayoutManager(this));
            adapteracc.setonListener(this);
            edtsearch.setText("Tìm tên hoặc số điện thoại bệnh nhân");
            // size list



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
            edtsearch.setText("Tìm cơ sở y tế");
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
                    filterList(query, "benh nhan");
                } else if (manager != null && manager.equals("quanlycsyt")) {
                    filterListcsyt(query);
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
        listacc.addAll(helper.getaccoutbyrole(role));

        if (query.isEmpty()) {
            adapteracc.notifyDataSetChanged();
              // Hiển thị tất cả nếu không nhập gì
        } else {
            for (accout item : listacc) {
                // Kiểm tra nếu tên bác sĩ hoặc chuyên khoa chứa từ khóa tìm kiếm
                if (item.getName().toLowerCase().contains(query.toLowerCase()) ||
                        item.getPhone().toLowerCase().contains(query.toLowerCase())) {
                    newlist.add(item);
                }
            }
            listacc.clear();
            listacc.addAll(newlist);
            adapteracc.notifyDataSetChanged();
        }


    }

    private void filterListcsyt(String query) {

        ArrayList<Cosoyte> newlist = new ArrayList();
        listcsyte.clear();
        listcsyte.addAll(helper.getCosoyte());

        if (query.isEmpty()) {
            adapteracc.notifyDataSetChanged();
            // Hiển thị tất cả nếu không nhập gì
        } else {
            for (Cosoyte item : listcsyte) {
                // Kiểm tra nếu tên bác sĩ hoặc chuyên khoa chứa từ khóa tìm kiếm
                if (item.getName().toLowerCase().contains(query.toLowerCase()) )
                    newlist.add(item);
                }
            }
            listcsyte.clear();
            listcsyte.addAll(newlist);
            adapteracc.notifyDataSetChanged();
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
        if( manager != null && manager.equals("quanlybenhnhan")){
            loadDatabenhnha();
        }

    }

    public void loadDatacsyt(){
        listcsyte.clear();
        listcsyte.addAll(helper.getCosoyte());
        adaptercsyt.notifyDataSetChanged();

    }

    public void loadDatabs(){
        listacc.clear();
        listacc.addAll(helper.getaccoutbyrole("bacsi"));
        adapteracc.notifyDataSetChanged();

    }

    public void loadDatabenhnha(){
        listacc.clear();
        listacc.addAll(helper.getaccoutbyrole("user"));
        adapteracc.notifyDataSetChanged();

    }


    private void anhxa() {
        toolbar = findViewById(R.id.tbqlbsad);
        rcvlisbsad = findViewById(R.id.rcvbsad);
        edtsearch = findViewById(R.id.edtsearchad);
        helper = new DatabaseHelper(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());



    }


    @Override
    public void onFixClick(int position) {
        if(manager != null && manager.equals("quanlybacsi")){
            String sdt = listacc.get(position).getPhone();
            Bacsi bacsi = helper.getBacsiBySdt(sdt);
            Intent intent = new Intent(adDanhsachBs.this, SuaBS.class);
            intent.putExtra("bacsi", bacsi);
            startActivity(intent);
        }  else{
            // an thanh sua
            Toast.makeText(this, "Bạn không có quyền sửa tài khoản này", Toast.LENGTH_SHORT).show();

        }

    }

    // xoa accout tren firebase


    @Override
    public void onDeleteClick(int position) {
        String sdt = listacc.get(position).getPhone();
        String role = listacc.get(position).getAs();
        helper.deleteaccout(sdt);
        if (role.equals("bacsi")){
            helper.deleteBacsi(sdt);
            loadDatabs();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            // xoa tai khoan tren firebase realtime

        } else {
            helper.deletebenhnhan(sdt);
            loadDatabenhnha();
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        }
        reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.child(sdt).removeValue();



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
                    adapteracc.notifyDataSetChanged(); // Cập nhật lại RecyclerView

                    // Nếu bạn lưu trạng thái vào database, cần gọi hàm cập nhật database ở đây
                    helper.updateaccout(acc);
                    // update len firebase
                    reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(acc.getPhone()).child("status").setValue(newStatus);

                })
                .show();
    }




    @Override
    public void onItemClick(int position) {
        // get bacsi by sdt
       if(manager != null && manager.equals("quanlybacsi")){
           String sdt = listacc.get(position).getPhone();
           Bacsi bacsi = helper.getBacsiBySdt(sdt);
           Intent intent = new Intent(adDanhsachBs.this, Bacsi_details.class);
           intent.putExtra("bacsi",bacsi);
           startActivity(intent);
       }
       else if(manager != null && manager.equals("quanlybenhnhan")){
           Toast.makeText(this, "Bạn không có quyền xem tài khoản này", Toast.LENGTH_SHORT).show();

        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        if (roleadmin == "admin" && manager != null && manager.equals("quanlycsyt")) {
            getMenuInflater().inflate(R.menu.addmenu, menu);
        }
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.plusss) {
            // add
            Intent intent1 = new Intent(adDanhsachBs.this, AddCSYT.class);
            startActivity(intent1);
            finish();


        }


        return super.onOptionsItemSelected(item);
    }



}