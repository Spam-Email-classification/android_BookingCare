package com.example.bookingcare263.ui.uiuser;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingcare263.R;

public class LienHe extends AppCompatActivity {
    private TextView tvLienHe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lienhe);

        tvLienHe = findViewById(R.id.tvLienHe);
        tvLienHe.setText(Html.fromHtml(
                "<b>Liên hệ</b><br>" +
                        "Nền tảng Đặt khám BookingCare<br> <br>" +
                        "<br>" +
                        "<b>ĐT: 02473.012.468</b>" +
                        "<br>" +
                        "Email: <a href=\"mailto:support@bookingcare.vn\">support@bookingcare.vn</a><br>" +
                        "<br>" +
                        "Trực thuộc: " +
                        "Công ty CP Công nghệ BookingCare<br>" +
                        "<br>" +
                        "ĐKKD số: 0106790291, Sở KH-ĐT Hà Nội cấp ngày: 16/03/2015<br>" +
                        "<br>" +
                        "Địa chỉ: Lô B4/D21, Khu đô thị mới Cầu Giấy, Phường Dịch Vọng Hậu, Quận Cầu Giấy, Thành phố Hà Nội, Việt Nam"
        ));
    }
}