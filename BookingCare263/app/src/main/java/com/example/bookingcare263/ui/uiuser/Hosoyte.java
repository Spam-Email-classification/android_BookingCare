package com.example.bookingcare263.ui.uiuser;

import static com.example.bookingcare263.UserActivity.iduser;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.benhnhan;
import com.example.bookingcare263.ui.Xuly;

import java.util.Calendar;

public class Hosoyte extends AppCompatActivity {
    Toolbar tbhsyt;
    ImageView imghsyt;
    EditText edthotencsyt, edtdiachiahsyt, edtngaysinhhsyt, edtbenhlyne;
    TextView edtsdthsyt;
    Button btnluuhsyt;
    RadioGroup rdgioitinh;
    Uri imageUri;
    String avatar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hosoyte);
        anhxa();




        // get benhnhan by iduser
        FirebaseHelper.getbenhnhanBySdt(iduser, new FirebaseCallBack<benhnhan>() {
            @Override
            public void onSuccess(benhnhan data) {


                edthotencsyt.setText(data.getTen());
                edtsdthsyt.setText(data.getSodienthoai());
                edtdiachiahsyt.setText(data.getDiachi());
                edtngaysinhhsyt.setText(data.getNgaysinh());
                edtbenhlyne.setText(data.getBenhlynen());

                avatar = data.getImg();
                if (avatar != null && !avatar.isEmpty()) {
                    Glide.with(imghsyt.getContext())
                            .load(Uri.parse(avatar)) // Chuyển String thành Uri
                            .circleCrop()
                            .into(imghsyt);
                } else{
                    imghsyt.setImageResource(R.drawable.baseline_account_circle_24);
                }

                if(data.getGioitinh()!= null &&  data.getGioitinh().equals("Nam")){
                    rdgioitinh.check(R.id.rdbtnnam);
                }else{
                    rdgioitinh.check(R.id.rdbtnnu);
                }


            }

            @Override
            public void onFailed(String message) {

            }
        });



        imghsyt.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });





        btnluuhsyt.setOnClickListener(e->{
            String ten = edthotencsyt.getText().toString();
            String sdt = edtsdthsyt.getText().toString();



            String diachi = edtdiachiahsyt.getText().toString();
            String ngaysinh = edtngaysinhhsyt.getText().toString();
            String benhlynen = edtbenhlyne.getText().toString();
            // get gioi tinh
            int selectedId = rdgioitinh.getCheckedRadioButtonId();
            String gioitinh = "";
            if (selectedId == R.id.rdbtnnam) {
                gioitinh = "Nam";
            } else if (selectedId == R.id.rdbtnnu) {
                gioitinh = "Nữ";
            }

            if (imageUri != null) {
                avatar = imageUri.toString();
            }

            benhnhan bn = new benhnhan(ten, sdt, diachi, gioitinh, ngaysinh, benhlynen, avatar);

            FirebaseHelper.updatebenhnha(bn, new FirebaseCallBack() {
                @Override
                public void onSuccess(Object data) {

                }

                @Override
                public void onFailed(String message) {

                }
            });
            finish();
        });

    }

    private void anhxa() {
        tbhsyt = findViewById(R.id.tbhsyt);
        imghsyt = findViewById(R.id.imghsyt);
        edthotencsyt = findViewById(R.id.edthotencsyt);
        edtsdthsyt = findViewById(R.id.edtsdthsyt);
        edtdiachiahsyt = findViewById(R.id.edtdiachiahsyt);
        edtngaysinhhsyt = findViewById(R.id.edtngaysinhhsyt);
        edtbenhlyne = findViewById(R.id.edtbenhlyne);
        btnluuhsyt = findViewById(R.id.btnluuhsyt);
        rdgioitinh = findViewById(R.id.rdgioitinh);


        int selectedId = rdgioitinh.getCheckedRadioButtonId();
        //
        edtngaysinhhsyt.setOnClickListener(e->showDatePickerDialog());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = Xuly.copyImageToInternalStorage(this, imageUri);// Lấy URI của ảnh
            Glide.with(this)
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imghsyt);        }
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        // Hiển thị DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            edtngaysinhhsyt.setText(selectedDate);  // Hiển thị ngày chọn trong EditText
        }, year, month, day);

        // Giới hạn ngày: Không cho chọn nhưngx ngày sau ngày hôm nay
        Calendar minDateCalendar = Calendar.getInstance();
        minDateCalendar.add(Calendar.DAY_OF_MONTH, 1);
        datePickerDialog.getDatePicker().setMinDate(minDateCalendar.getTimeInMillis());

        datePickerDialog.show();
    }

}