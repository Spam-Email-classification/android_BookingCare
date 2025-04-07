package com.example.bookingcare263.ui.adminui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.ui.Xuly;

public class AddCSYT extends AppCompatActivity {

    Toolbar tbaddcsyt;
    ImageView imgavatar4csyt;
    EditText edttencsyt, edtsdtadd4csyt, edtemailadd4csyt, edtdiachicsyt, edtchuyenkhoacsyt, edtmasogiayphepcsyt, edtwebsitecsyt, edtthongtin4csyt;
    Button btnThem4csyt;

    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_csyt);

        anhhxa();
        imgavatar4csyt.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });

        btnThem4csyt.setOnClickListener(v->{
            if (!validate1()){
                return;
            }
            String avatar = "";
            if (imageUri != null) {
                avatar = imageUri.toString();
            }
            Cosoyte csyt = new Cosoyte(

                    edttencsyt.getText().toString(),
                    avatar,
                    edtdiachicsyt.getText().toString(),
                    edtchuyenkhoacsyt.getText().toString(),
                    edtmasogiayphepcsyt.getText().toString(),
                    edtwebsitecsyt.getText().toString(),
                    edtthongtin4csyt.getText().toString(),
                    edtsdtadd4csyt.getText().toString(),
                    edtemailadd4csyt.getText().toString()
            );

            FirebaseHelper.insertcosoyte(csyt, new FirebaseCallBack() {
                @Override
                public void onSuccess(Object data) {

                }

                @Override
                public void onFailed(String message) {

                }
            });


            Intent intent = new Intent(AddCSYT.this, adDanhsachBs.class);
            intent.putExtra("manager", "quanlycsyt");
            startActivity(intent);
            finish();

        });



    }

    public boolean validate1(){
        if (edttencsyt.getText().toString().isEmpty()){
            edttencsyt.setError("Vui lòng nhập tên");
            return false;
        }
        if (edtdiachicsyt.getText().toString().isEmpty()){
            edtdiachicsyt.setError("Vui lòng nhập địa chỉ");
            return false;
        }
        if (edtchuyenkhoacsyt.getText().toString().isEmpty()){
            edtchuyenkhoacsyt.setError("Vui lòng nhập chuyên khoa");
            return false;
        }
        if (edtmasogiayphepcsyt.getText().toString().isEmpty()){
            edtmasogiayphepcsyt.setError("Vui lòng nhập mã số giấy phép");
            return false;
        }
        if (edtwebsitecsyt.getText().toString().isEmpty()){
            edtwebsitecsyt.setError("Vui lòng nhập website");
            return false;
        }
        if (edtthongtin4csyt.getText().toString().isEmpty()){
            edtthongtin4csyt.setError("Vui lòng nhập thông tin");
            return false;
        }
        if (edtsdtadd4csyt.getText().toString().isEmpty()){
            edtsdtadd4csyt.setError("Vui lòng nhập số điện thoại");
            return false;
        }
        if (edtemailadd4csyt.getText().toString().isEmpty()){
            edtemailadd4csyt.setError("Vui lòng nhập email");
            return false;
        }
        return true;



    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = Xuly.copyImageToInternalStorage(this, imageUri);// Lấy URI của ảnh
            Glide.with(imgavatar4csyt.getContext())
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgavatar4csyt);        }
    }


    private void anhhxa() {
        imgavatar4csyt = findViewById(R.id.imgavatar4csyt);
        tbaddcsyt = findViewById(R.id.tbaddcsyt);
        edttencsyt = findViewById(R.id.edttencsyt);
        edtdiachicsyt = findViewById(R.id.edtdiachicsyt);
        edtchuyenkhoacsyt = findViewById(R.id.edtchuyenkhoacsyt);
        edtmasogiayphepcsyt = findViewById(R.id.edtmasogiayphepcsyt);
        edtwebsitecsyt = findViewById(R.id.edtwebsitecsyt);
        edtthongtin4csyt = findViewById(R.id.edtthongtin4csyt);
        edtsdtadd4csyt = findViewById(R.id.edtsdtadd4csyt);
        edtemailadd4csyt = findViewById(R.id.edtemailadd4csyt);

        btnThem4csyt = findViewById(R.id.btnThem4csyt);
        tbaddcsyt.setNavigationOnClickListener(view -> {
            finish();
        });

    }
}