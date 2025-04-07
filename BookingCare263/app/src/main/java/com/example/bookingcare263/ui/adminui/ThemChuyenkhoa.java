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
import com.example.bookingcare263.model.chuyenkhoa;
import com.example.bookingcare263.ui.Xuly;

public class ThemChuyenkhoa extends AppCompatActivity {
    Toolbar tbAddchuyenkhoa;
    ImageView imgavataraddchuyenkhoa;
    EditText edttenaddchuyenkhoa, edtthongtinaddchuyenkhoa;
    Button btnaddchuyenkhoa;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_chuyenkhoa);
        anhxa();

        imgavataraddchuyenkhoa.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });

        btnaddchuyenkhoa.setOnClickListener(e->{
            String tenchuyenkhoa = edttenaddchuyenkhoa.getText().toString();
            String thongtin = edtthongtinaddchuyenkhoa.getText().toString();
            if(!validate1()){
                return;
            }
            String avatar = "";
            if(imageUri != null) {
                avatar = imageUri.toString();
            }
            chuyenkhoa ck = new chuyenkhoa(tenchuyenkhoa, avatar, thongtin);

            FirebaseHelper.addchuyenkhoa(ck, new FirebaseCallBack() {
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

    public boolean validate1(){
        String tenchuyenkhoa = edttenaddchuyenkhoa.getText().toString();
        String thongtin = edtthongtinaddchuyenkhoa.getText().toString();

        if(tenchuyenkhoa.isEmpty()){
            edttenaddchuyenkhoa.setError("Vui lòng nhập tên chuyên khoa");
            return false;
        }
        if(thongtin.isEmpty()){
            edtthongtinaddchuyenkhoa.setError("Vui lòng nhập thông tin chuyên khoa");
            return false;
        }
        return true;

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = Xuly.copyImageToInternalStorage(this, imageUri);// Lấy URI của ảnh
            Glide.with(this)
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgavataraddchuyenkhoa);        }
    }


    private void anhxa() {
        tbAddchuyenkhoa = findViewById(R.id.tbaddchuyenkhoa);
        imgavataraddchuyenkhoa = findViewById(R.id.imgavataraddchuyenkhoa);
        edttenaddchuyenkhoa = findViewById(R.id.edttenaddchuyenkhoa);
        edtthongtinaddchuyenkhoa = findViewById(R.id.edtthongtinaddchuyenkhoa);
        btnaddchuyenkhoa = findViewById(R.id.btnaddchuyenkhoa);

        setSupportActionBar(tbAddchuyenkhoa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbAddchuyenkhoa.setNavigationOnClickListener(v -> onBackPressed());



    }
}