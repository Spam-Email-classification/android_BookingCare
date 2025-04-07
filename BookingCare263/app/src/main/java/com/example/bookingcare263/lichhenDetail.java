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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.lichhen;

public class lichhenDetail extends AppCompatActivity {

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
        txttenBacSi.setText(lh.getNamebs());
        txttenBenhNhan.setText(lh.getNamebenhnhan());
        txtsdtBenhNhan.setText(lh.getSdtbenhnhan());
        txtdiaChiKham.setText(lh.getDiachibenhnhan());
        txtngayhenkham.setText(lh.getNgayhenkham());
        txtgiohenkham.setText(lh.getKhunggiokham());
        txttrangThai.setText(lh.getTrangthai());

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

            finish();
        });
        if(!UserActivity.roleuser.equals("bacsi") || lh.getTrangthai().equals("Xác nhận")){
            btnxacnhan.setVisibility(View.GONE);
        }



        btnxacnhan.setOnClickListener(v -> {
            FirebaseHelper.updateTrangThai(lh.getId(), "Xác nhận");
            finish();
        });

    }

    private void anhxa() {
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
    }
}
