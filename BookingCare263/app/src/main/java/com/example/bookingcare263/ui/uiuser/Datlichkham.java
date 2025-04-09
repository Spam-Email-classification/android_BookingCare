package com.example.bookingcare263.ui.uiuser;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.FCMHelper;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.model.benhnhan;
import com.example.bookingcare263.model.lichhen;
import com.example.bookingcare263.UserActivity;

import java.util.Calendar;

public class Datlichkham extends AppCompatActivity {
    private TextView txtDescription;
    private TextView txtSeeMore, txtTime, txtAddress, txtNameBs, txtchuyenkhoa, txtgiakhamdk, txtgiakham, txttong;
    private Button btndatlichkham;
    ImageView imgavtars;
    EditText edthoten, edtsdt, edtngay, edtdiachi;


    // xly radio group
     RadioGroup radioGroupGioKham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datlichkham);

        anhxa();

        Intent intent = getIntent();
        Bacsi bacsi = (Bacsi) intent.getSerializableExtra("bacsi");


        txtDescription.setText(bacsi.getThongtin());

        txtNameBs.setText(bacsi.getName());
        txtchuyenkhoa.setText(bacsi.getChuyenkhoa());
        txtgiakhamdk.setText(String.valueOf(bacsi.getGiaKham()));
        txtgiakham.setText(String.valueOf(bacsi.getGiaKham()));
        txttong.setText(String.valueOf(bacsi.getGiaKham()));


        // xu ly radiogroup
        int selectedid = radioGroupGioKham.getCheckedRadioButtonId();

        RadioButton selectedRadioButton = findViewById(selectedid);
        String selectedText = selectedRadioButton.getText().toString();

        Glide.with(this)
                .load(Uri.parse(bacsi.getImg())) // Chuyển String thành Uri
                .circleCrop()
                .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                .into(imgavtars);
        edtngay.setOnClickListener(e->showDatePickerDialog());

        FirebaseHelper.getbenhnhanBySdt(UserActivity.iduser, new FirebaseCallBack<benhnhan>() {
            @Override
            public void onSuccess(benhnhan data) {
                edthoten.setText(data.getTen());
                edtsdt.setText(data.getSodienthoai());
                edtdiachi.setText(data.getDiachi());
            }

            @Override
            public void onFailed(String message) {

            }
        });



        btndatlichkham.setOnClickListener(e->{

            String iduser = UserActivity.iduser;
            if(!validate()) return;

            lichhen lh = new lichhen(
                    iduser,
                    bacsi.getSdt(),
                    edtngay.getText().toString(),
                    selectedText,                    "Đang chờ",
                    edthoten.getText().toString(),
                    edtsdt.getText().toString(),
                    edtdiachi.getText().toString(),
                    bacsi.getImg(),
                    bacsi.getName()
            );

            FirebaseHelper.addlichhen(lh, new FirebaseCallBack() {
                @Override
                public void onSuccess(Object data) {


                }

                @Override
                public void onFailed(String message) {

                }
            });

            FirebaseHelper.getaccbyid(bacsi.getSdt(), new FirebaseCallBack<accout>() {
                @Override
                public void onSuccess(accout data) {
                    String token = data.getToken();
                    FCMHelper.sendNotification( Datlichkham.this,token, "Bạn có lịch hẹn mới", "vui lòng xác nhận");
                }

                @Override
                public void onFailed(String message) {

                }
            });
            finish();

        });


    }



    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        // Hiển thị DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            edtngay.setText(selectedDate);  // Hiển thị ngày chọn trong EditText
        }, year, month, day);

        // Giới hạn ngày: Chỉ cho phép chọn từ ngày hôm nay trở đi
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        datePickerDialog.show();
    }

    private boolean validate(){
        String hoten = edthoten.getText().toString();
        String sdt = edtsdt.getText().toString();
        String ngay = edtngay.getText().toString();
        String diachi = edtdiachi.getText().toString();
        if (hoten.isEmpty()){
            edthoten.setError("Vui lòng nhập họ tên");
            return false;
        }
        if (sdt.isEmpty()){
            edtsdt.setError("Vui lòng nhập số điện thoại");
            return false;
        }
        if (ngay.isEmpty()){
            edtngay.setError("Vui lòng nhập ngày");
            return false;
        }
        if (diachi.isEmpty()){
            edtdiachi.setError("Vui lòng nhập địa chỉ");
            return false;
        }
        else {
            return true;
        }
    }

    private void anhxa() {
        txtDescription = findViewById(R.id.txtthongtin);
        txtSeeMore = findViewById(R.id.btmxemthem);
        txtTime = findViewById(R.id.txttime);
        txtAddress = findViewById(R.id.txtdiachi);
        txtNameBs = findViewById(R.id.txtnamebs);
        txtchuyenkhoa = findViewById(R.id.txtchuyenkhoa1);
        txtgiakhamdk = findViewById(R.id.txtgiakhamdk);
        txtgiakham = findViewById(R.id.txtgiakham);
        txttong = findViewById(R.id.txttong);
        btndatlichkham = findViewById(R.id.btnxnkham);
        imgavtars = findViewById(R.id.imgavtarbsdt2);
        edthoten = findViewById(R.id.edthoten);
        edtsdt = findViewById(R.id.edtsdt);
        edtngay = findViewById(R.id.edtngay);
        edtdiachi = findViewById(R.id.textView15);
        radioGroupGioKham = findViewById(R.id.radioGroupGioKham);


        txtSeeMore.setOnClickListener(v -> {
            if (txtDescription.getMaxLines() == 2) {
                txtDescription.setMaxLines(Integer.MAX_VALUE); // Hiển thị toàn bộ nội dung
                txtSeeMore.setText("Thu gọn");
            } else {
                txtDescription.setMaxLines(2); // Giới hạn lại
                txtSeeMore.setText("Xem thêm");
            }
        });


    }
}