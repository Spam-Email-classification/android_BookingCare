package com.example.bookingcare263.ui.bacsiui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.ui.copyImage;

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

        // set name and avatar
        txtnametbv.setText("Bác sĩ: " + bacsi.getName());
        String avatar = bacsi.getImg();
        if(avatar != null &&  !avatar.isEmpty())
            Glide.with(imgavatartbv.getContext())
                    .load(Uri.parse(avatar)) // Chuyển String thành ặc đUri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mịnh nếu load thất bại
                    .into(imgavatartbv);
        else{
            imgavatartbv.setImageResource(R.drawable.baseline_account_circle_24);
        }

        imgavatartbv.setOnClickListener(view -> {
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
                anh = null;
            }
            Baiviet baiviet = new Baiviet(  bacsi.getId(), content, timestamp,edtbaibnvt.getText().toString(), anh);
            if(helper.insertbaiviet(baiviet, bacsi.getId())){
                Toast.makeText(this, "Đăng bài thành công", Toast.LENGTH_SHORT).show();
            }
            finish();

        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = copyImage.copyImageToInternalStorage(this, imageUri);// Lấy URI của ảnh
            Glide.with(this)
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
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
        helper = new DatabaseHelper(this);


    }
}