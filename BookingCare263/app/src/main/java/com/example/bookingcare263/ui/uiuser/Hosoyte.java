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
import com.example.bookingcare263.DatabaseHelper;
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
    DatabaseHelper helper;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hosoyte);
        anhxa();



        benhnhan bn1 = helper.getbenhnhan(iduser);
        edthotencsyt.setText(bn1.getTen());
        edtsdthsyt.setText(bn1.getSodienthoai());
        edtdiachiahsyt.setText(bn1.getDiachi());
        edtngaysinhhsyt.setText(bn1.getNgaysinh());
        edtbenhlyne.setText(bn1.getBenhlynen());

        String avatar1 = bn1.getImg();
        if (avatar1 != null && !avatar1.isEmpty()) {
            Glide.with(this)
                    .load(Uri.parse(avatar1)) // Chuyển String thành Uri
                    .circleCrop()
                    .into(imghsyt);
        } else{
            imghsyt.setImageResource(R.drawable.baseline_account_circle_24);
        }

        if(bn1.getGioitinh()!= null &&  bn1.getGioitinh().equals("Nam")){
            rdgioitinh.check(R.id.rdbtnnam);
        }else{
            rdgioitinh.check(R.id.rdbtnnu);
        }

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
            String avatar = "";
            if (imageUri != null) {
                avatar = imageUri.toString();
            }
            benhnhan bn = new benhnhan(ten, sdt, diachi, gioitinh, ngaysinh, benhlynen, avatar);
            helper.updatebenhnha(bn);
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
        helper = new DatabaseHelper(this);


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