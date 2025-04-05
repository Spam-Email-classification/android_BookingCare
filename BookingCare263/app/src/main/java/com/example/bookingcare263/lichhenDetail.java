package com.example.bookingcare263;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.model.lichhen;

public class lichhenDetail extends AppCompatActivity {

    private ImageView anhBacSi;
    private TextView tenBacSi;
    private TextView tenBenhNhan;
    private TextView sdtBenhNhan;
    private TextView diaChiKham;
    private TextView ngayhenkham;
    private TextView giohenkham;
    private TextView trangThai;
    private Button nutHuyLichHen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lichhen_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AnhXa();
        loadDataFromIntent();
    }

    private void AnhXa() {
        anhBacSi = findViewById(R.id.anhBacSi);
        tenBacSi = findViewById(R.id.tenBacSi);
        tenBenhNhan = findViewById(R.id.tenBenhNhan);
        sdtBenhNhan = findViewById(R.id.soDienThoai);
        diaChiKham = findViewById(R.id.diaDiem);
        ngayhenkham = findViewById(R.id.ngayHenKham);
        giohenkham = findViewById(R.id.gioHenKham);
        trangThai = findViewById(R.id.trangThai);
        nutHuyLichHen = findViewById(R.id.nutHuyLichHen);
        nutHuyLichHen.setOnClickListener(v -> huyLichHen());
    }

    private void loadDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            lichhen lichHen = (lichhen) intent.getSerializableExtra("lichhen");
            String imagePath = intent.getStringExtra("anhbacsi");
            if (imagePath != null) {

                tenBacSi.setText(lichHen.getNamebs());
                tenBenhNhan.setText(lichHen.getNamebenhnhan());
                sdtBenhNhan.setText(lichHen.getSdtbenhnhan());
                diaChiKham.setText(lichHen.getDiachibenhnhan());
                ngayhenkham.setText(lichHen.getNgayhenkham());
                giohenkham.setText(lichHen.getKhunggiokham());
                trangThai.setText(lichHen.getTrangthai());
                if (imagePath.startsWith("android.resource://")) {
                    Uri uri = Uri.parse(imagePath);
                    Glide.with(this).load(uri).into(anhBacSi);
                } else {
                    anhBacSi.setImageURI(Uri.parse(imagePath));
                }
            } else {
                anhBacSi.setImageResource(R.drawable.plus);
            }
        }
    }

    private void huyLichHen() {
        int lichHenId = getIntent().getIntExtra("id", -1);

        if (lichHenId != -1) {
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.updateTrangThaiLichHen(String.valueOf(lichHenId), "Đã hủy");

            // Cập nhật giao diện
            trangThai.setText("Trạng thái: Đã hủy");
            trangThai.setTextColor(ContextCompat.getColor(this, R.color.red));

            // Vô hiệu hóa nút
            nutHuyLichHen.setEnabled(false);
            nutHuyLichHen.setText("Đã hủy");
            nutHuyLichHen.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.purple_700));

            Toast.makeText(this, "Đã hủy lịch hẹn với ID: " + lichHenId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Không tìm thấy lịch hẹn để hủy", Toast.LENGTH_SHORT).show();
        }
    }
}
