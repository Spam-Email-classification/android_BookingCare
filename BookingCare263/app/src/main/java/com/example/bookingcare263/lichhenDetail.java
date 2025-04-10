package com.example.bookingcare263;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.model.lichhen;
import com.example.bookingcare263.ui.uiuser.Datlichkham;

public class lichhenDetail extends AppCompatActivity {
    Toolbar toolbar;

    private ImageView imganhBacSi;
    private TextView txttenBacSi;
    private TextView txttenBenhNhan;
    private TextView txtsdtBenhNhan;
    private TextView txtdiaChiKham;
    private TextView txtngayhenkham;
    private TextView txtgiohenkham;
    private TextView txttrangThai;
    private Button btnnutHuyLichHen, btnxacnhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lichhen_detail);


        anhxa();

        Intent intent = getIntent();
        lichhen lh = (lichhen) intent.getSerializableExtra("lichhen");
        Toast.makeText(this, "idlich hen" + lh.getId(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "idlich name" + lh.getIdbacsi(), Toast.LENGTH_SHORT).show();

        // load thong tin
        txttenBacSi.setText( lh.getNamebs());
        txttenBenhNhan.setText("Tên bệnh nhân: " + lh.getNamebenhnhan());
        txtsdtBenhNhan.setText("Ngày hẹn khám: " + lh.getSdtbenhnhan());
        txtdiaChiKham.setText("Giờ hẹn khám " + lh.getDiachibenhnhan());
        txtngayhenkham.setText("Địa điểm: " + lh.getNgayhenkham());
        txtgiohenkham.setText("Số điện thoại: " + lh.getKhunggiokham());
        txttrangThai.setText("Trạng thái" + lh.getTrangthai());

        if(lh.getAvatarbs()!=null)
            Glide.with(imganhBacSi.getContext())
                    .load(Uri.parse(lh.getAvatarbs().toString())) // Chuyển String thành ặc đUri
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mịnh nếu load thất bại
                    .into(imganhBacSi);
        else{
            imganhBacSi.setImageResource(R.drawable.imagechose);
        }
        btnnutHuyLichHen.setOnClickListener(v -> {

          FirebaseHelper.deletelichhen(lh.getId());
          FirebaseHelper.getaccbyid(lh.getIdbenhnhan(), new FirebaseCallBack<accout>() {
                @Override
                public void onSuccess(accout data) {
                    String token = data.getToken();
                    FCMHelper.sendNotification( lichhenDetail.this, token,  "bacsi: " + lh.getNamebs(), "đã hủy lichhen");

                }

                @Override
                public void onFailed(String message) {

                }
            });
            finish();
        });
        if(UserActivity.roleuser.equals("user") || lh.getTrangthai().equals("Xác nhận")){
            btnxacnhan.setVisibility(View.GONE);
        }



        btnxacnhan.setOnClickListener(v -> {
            FirebaseHelper.updateTrangThai(lh.getId(), "Xác nhận");

            // get token by idbenhnhan
            FirebaseHelper.getaccbyid(lh.getIdbenhnhan(), new FirebaseCallBack<accout>() {
                @Override
                public void onSuccess(accout data) {
                    String token = data.getToken();
                    FCMHelper.sendNotification( lichhenDetail.this, token, "Lịch hẹn đã được " + lh.getNamebs(), "xác nhận");
                }

                @Override
                public void onFailed(String message) {

                }
            });


            finish();
        });

    }

    private void anhxa() {
        toolbar = findViewById(R.id.thanhCongCu);
        imganhBacSi = findViewById(R.id.anhBacSi);
        txttenBacSi = findViewById(R.id.tenBacSi);
        txttenBenhNhan = findViewById(R.id.tenBenhNhan);
        txtsdtBenhNhan = findViewById(R.id.soDienThoai);
        txtdiaChiKham = findViewById(R.id.diaDiem);
        txtngayhenkham = findViewById(R.id.ngayHenKham);
        txtgiohenkham = findViewById(R.id.gioHenKham);
        txttrangThai = findViewById(R.id.trangThai);
        btnnutHuyLichHen = findViewById(R.id.nutHuyLichHen);
        btnxacnhan = findViewById(R.id.btnxacnhan);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
