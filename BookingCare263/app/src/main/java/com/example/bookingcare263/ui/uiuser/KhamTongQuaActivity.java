package com.example.bookingcare263.ui.uiuser;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingcare263.R;

public class KhamTongQuaActivity extends AppCompatActivity {

    private EditText thanhTimKiem;
    private Button nutKhuVuc, nutDanhMuc, nutGia, nutCoSoYTe;
    private HorizontalScrollView thanhCuonDanhMuc, thanhCuonGoiNoiBat, thanhCuonCoSoYTe;
    private Spinner spinRole; // spinner mà bạn muốn sử dụng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kham_tong_qua); // Giả sử layout của bạn là activity_kham_tong_quat.xml

        // Khởi tạo các view
        thanhTimKiem = findViewById(R.id.thanh_tim_kiem);
        nutKhuVuc = findViewById(R.id.nut_khu_vuc);
        nutDanhMuc = findViewById(R.id.nut_danh_muc);
        nutGia = findViewById(R.id.nut_gia);
        nutCoSoYTe = findViewById(R.id.nut_co_so_y_te);
        thanhCuonDanhMuc = findViewById(R.id.thanh_cuon_danh_muc);
        thanhCuonGoiNoiBat = findViewById(R.id.thanh_cuon_goi_noi_bat);
        thanhCuonCoSoYTe = findViewById(R.id.thanh_cuon_co_so_y_te);

        // Xử lý sự kiện nút
        nutKhuVuc.setOnClickListener(view -> {
            // Mở hoặc hiển thị menu chọn khu vực
        });

        nutDanhMuc.setOnClickListener(view -> {
            // Mở hoặc hiển thị menu chọn danh mục
        });

        nutGia.setOnClickListener(view -> {
            // Mở hoặc hiển thị menu chọn mức giá
        });

        nutCoSoYTe.setOnClickListener(view -> {
            // Mở hoặc hiển thị menu chọn cơ sở y tế
        });
    }
}
