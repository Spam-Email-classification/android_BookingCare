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
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.ui.Xuly;

public class SuaCSYT extends AppCompatActivity {
    private Toolbar tbsuacsyt;
    private ImageView imgavatarsuacsyt;

    EditText edttencsytsua, edtsdtcsytsua, edtemailaddcsytsua, edtdiachicsytsua, edtthongtin4csytsua
            , edtchuyenkhoacsytsua, edtmasogiayphepcsytsua, edtwebsitecsytsua;

    Button btncsytsua;
    DatabaseHelper helper;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_csyt);
        anhxa();
        Intent intent = getIntent();
        Cosoyte csyt = (Cosoyte) intent.getSerializableExtra("cosoyte");
        edttencsytsua.setText(csyt.getName());
        edtsdtcsytsua.setText(csyt.getSdt());
        edtemailaddcsytsua.setText(csyt.getEmail());
        edtdiachicsytsua.setText(csyt.getDiachi());
        edtchuyenkhoacsytsua.setText(csyt.getChuyenkhoa());
        edtmasogiayphepcsytsua.setText(csyt.getMasogiayphep());
        edtwebsitecsytsua.setText(csyt.getWebsite());
        edtthongtin4csytsua.setText(csyt.getThongtin());
        Glide.with(imgavatarsuacsyt.getContext())
                .load(Uri.parse(csyt.getImg())) // Chuyển String thành Uri
                .circleCrop()
                .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                .into(imgavatarsuacsyt);

        imgavatarsuacsyt.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });

        btncsytsua.setOnClickListener(e->{
            String ten = edttencsytsua.getText().toString();
            String sdt = edtsdtcsytsua.getText().toString();
            String email = edtemailaddcsytsua.getText().toString();
            String diachi = edtdiachicsytsua.getText().toString();
            String chuyenkhoa = edtchuyenkhoacsytsua.getText().toString();
            String masogiayphep = edtmasogiayphepcsytsua.getText().toString();
            String website = edtwebsitecsytsua.getText().toString();
            String thongtin = edtthongtin4csytsua.getText().toString();
            String avatar ="";
            if(imageUri != null){
                avatar = imageUri.toString();
            } else{
                avatar = csyt.getImg();
            }
            Cosoyte csyt1 = new Cosoyte(csyt.getId(), ten, avatar, diachi, thongtin, masogiayphep, website, sdt, email, chuyenkhoa);

            FirebaseHelper.updatcosoyte(csyt1, new FirebaseCallBack() {
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = Xuly.copyImageToInternalStorage(this, imageUri);// Lấy URI của ảnh
            Glide.with(imgavatarsuacsyt.getContext())
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgavatarsuacsyt);        }
    }


    private void anhxa() {
        tbsuacsyt = findViewById(R.id.tbsuacsyt);
        imgavatarsuacsyt = findViewById(R.id.imgavatarsuacsyt);
        edttencsytsua = findViewById(R.id.edttencsytsua);
        edtsdtcsytsua = findViewById(R.id.edtsdtcsytsua);
        edtemailaddcsytsua = findViewById(R.id.edtemailaddcsytsua);
        edtdiachicsytsua = findViewById(R.id.edtdiachicsytsua);
        edtchuyenkhoacsytsua = findViewById(R.id.edtchuyenkhoacsytsua);
        edtmasogiayphepcsytsua = findViewById(R.id.edtmasogiayphepcsytsua);
        edtwebsitecsytsua = findViewById(R.id.edtwebsitecsytsua);
        edtthongtin4csytsua = findViewById(R.id.edtthongtin4csytsua);
        btncsytsua = findViewById(R.id.btncsytsua);
        helper = new DatabaseHelper(this);


        setSupportActionBar(tbsuacsyt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbsuacsyt.setNavigationOnClickListener(v -> finish());

    }
}