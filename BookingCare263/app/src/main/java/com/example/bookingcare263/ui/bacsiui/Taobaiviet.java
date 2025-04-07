package com.example.bookingcare263.ui.bacsiui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.ui.Xuly;
import com.example.bookingcare263.UserActivity;

public class Taobaiviet extends AppCompatActivity {
    ImageView imganhbaiviet, imgavatartbv;
    TextView txtnametbv;
    Button btndangtai;
    EditText edtbaibnvt, edttitletbv;
    Uri imageUri;
    DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_taobaiviet);
        Intent intent = getIntent();
        Bacsi bacsi = (Bacsi) intent.getSerializableExtra("bacsi");
        // anh xa
        anhxa();

        // set name and avatar bac si
        txtnametbv.setText("Bác sĩ: " + bacsi.getName());
        String avatar = bacsi.getImg();
        if(avatar != null &&  !avatar.isEmpty()){
            Glide.with(imgavatartbv.getContext())
                    .load(Uri.parse(avatar)) // Chuyển String thành ặc đUri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mịnh nếu load thất bại
                    .into(imgavatartbv);}
        else{
            imgavatartbv.setImageResource(R.drawable.baseline_account_circle_24);
        }

        imganhbaiviet.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });

        btndangtai.setOnClickListener(view -> {
            String content = edtbaibnvt.getText().toString();
            // timstamp = thoi gian hien tai
            String timestamp = String.valueOf(System.currentTimeMillis());
            String anh;
            if(imageUri != null){
                anh = imageUri.toString();
            }else{
                anh = "";
            }
            Baiviet baiviet = new Baiviet(UserActivity.iduser, content, timestamp,edttitletbv.getText().toString(), anh);
            FirebaseHelper.addbaiviet(baiviet, new FirebaseCallBack() {
                @Override
                public void onSuccess(Object data) {
                    finish();

                }

                @Override
                public void onFailed(String message) {

                }
            });



        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = Xuly.copyImageToInternalStorage(this, imageUri);// Lấy URI của ảnh
            Glide.with(this)
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imganhbaiviet);        }
    }


    private void anhxa() {
        imganhbaiviet = findViewById(R.id.imganhbaiviet);
        imgavatartbv = findViewById(R.id.imgavatartbv);
        txtnametbv = findViewById(R.id.txtnametbv);
        btndangtai = findViewById(R.id.btndangtai);
        edtbaibnvt = findViewById(R.id.edtbaibnvttbv);
        edttitletbv = findViewById(R.id.txttitletbv);
        helper = new DatabaseHelper(this);


    }
}