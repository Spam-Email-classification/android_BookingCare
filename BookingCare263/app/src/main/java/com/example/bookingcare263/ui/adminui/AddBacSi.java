package com.example.bookingcare263.ui.adminui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.ui.copyImage;

public class AddBacSi extends AppCompatActivity {

    ImageView imgavatar;
    EditText edtten, edtsdtadd, edtemailadd, edtdiachi, edtchuyenkhoa, edtmasogiayphep, edtgiakhamadd, edtthongtin;
    Button btnThem;
    DatabaseHelper helper;

    private Uri imageUri;
    Spinner spinckbs;

    String [] items = {"Cơ xương khớp", "Thần kinh", "Tiêu hóa", "Tim mạch", "Cột sống", "Y học cổ truyền", "Châm cứu", "Sản phụ khoa", "Da liễu", "Hô hấp phổi", "Chuyên khoa mắt", "Thân - Tiết niệu", "Ung bướu" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_bac_si);
        anhxa();
        imgavatar.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });


        btnThem.setOnClickListener(v->{
            if (!validate()){
                return;
            }
            String avatar = "";
            if (imageUri != null) {
             avatar = imageUri.toString();
            }
            Bacsi bs = new Bacsi(
                    edtten.getText().toString(),
                    spinckbs.getSelectedItem().toString(),
                    edtdiachi.getText().toString(),
                    avatar,
                    edtthongtin.getText().toString(),
                    edtgiakhamadd.getText().toString(),
                    edtmasogiayphep.getText().toString(),
                    edtemailadd.getText().toString(),
                    edtsdtadd.getText().toString()
            );
            helper.addBacsi(bs);

            Intent intent = new Intent(AddBacSi.this, adDanhsachBs.class);
            intent.putExtra("manager", "quanlybacsi");
            startActivity(intent);
            finish();

        });
    }

    public boolean validate (){
        if (edtten.getText().toString().isEmpty()){
            edtten.setError("Vui lòng nhập tên");
            return false;
        }

        if (edtdiachi.getText().toString().isEmpty()){
            edtdiachi.setError("Vui lòng nhập địa chỉ");
            return false;
        }
        if (edtthongtin.getText().toString().isEmpty()){
            edtthongtin.setError("Vui lòng nhập thông tin");
            return false;
        }
        if (edtgiakhamadd.getText().toString().isEmpty()){
            edtgiakhamadd.setError("Vui lòng nhập giá khám");
            return false;
        }
        if (edtmasogiayphep.getText().toString().isEmpty()){
            edtmasogiayphep.setError("Vui lòng nhập mã số giấy phép");
            return false;
        }
        if (edtemailadd.getText().toString().isEmpty()){
            edtemailadd.setError("Vui lòng nhập email");
            return false;
        }
        if (edtsdtadd.getText().toString().isEmpty()){
            edtsdtadd.setError("Vui lòng nhập số điện thoại");
            return false;
        }
        return true;


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = copyImage.copyImageToInternalStorage(this, imageUri);// Lấy URI của ảnh
            Glide.with(imgavatar.getContext())
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgavatar);        }
    }

    private void anhxa() {
        imgavatar = findViewById(R.id.imgavatar4);
        edtten = findViewById(R.id.edtten4);
        edtsdtadd = findViewById(R.id.edtsdtadd4);
        edtemailadd = findViewById(R.id.edtemailadd4);
        edtdiachi = findViewById(R.id.edtdiachi);
        edtmasogiayphep = findViewById(R.id.edtmasogiayphep);
        edtgiakhamadd = findViewById(R.id.edtgiakhamadd);
        edtthongtin = findViewById(R.id.edtthongtin4);
        btnThem = findViewById(R.id.btnThem4);
        helper = new DatabaseHelper(this);
        spinckbs = findViewById(R.id.spinckbs);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinckbs.setAdapter(adapter);




    }
}