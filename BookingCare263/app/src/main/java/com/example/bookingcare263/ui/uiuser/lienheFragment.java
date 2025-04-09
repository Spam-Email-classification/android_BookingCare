package com.example.bookingcare263.ui.uiuser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookingcare263.R;
import com.example.bookingcare263.databinding.FragmentLichhenusBinding;
import com.example.bookingcare263.databinding.FragmentLienheBinding;

public class lienheFragment extends Fragment {
    FragmentLienheBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLienheBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        anhxa();

        return root;
    }

    private void anhxa() {
        TextView txtfragelienhe = binding.txtfraglienhe;
        txtfragelienhe.setText(Html.fromHtml(
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
        )); // Đối với Android API >= 24

// Cho phép click vào link
//        txtfragelienhe.setMovementMethod(LinkMovementMethod.getInstance());


    }



}