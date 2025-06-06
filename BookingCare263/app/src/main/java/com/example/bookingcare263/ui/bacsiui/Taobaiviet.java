package com.example.bookingcare263.ui.bacsiui;

import static com.example.bookingcare263.ui.Xuly.uploadImageToFirebaseStorage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.ui.Xuly;
import com.example.bookingcare263.UserActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Taobaiviet extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imganhbaiviet, imgavatartbv;
    TextView txtnametbv;
    Button btndangtai;
    EditText edtbaibnvt, edttitletbv;
    Uri imageUri;
    Baiviet baiviet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_taobaiviet);
        Intent intent = getIntent();
        Bacsi bacsi = (Bacsi) intent.getSerializableExtra("bacsi");
        // anh xa
        anhxa();
        baiviet = new Baiviet();

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
            baiviet.setContent(content);
            baiviet.setTimestamp(timestamp);
            baiviet.setIduser(UserActivity.iduser);
            baiviet.setTitile(edttitletbv.getText().toString());




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
            String uniquename = "image_" + System.currentTimeMillis() + ".jpg";
            uploadImageToFirebaseStorage(this, imageUri, uniquename, downloadUri -> {

                // upload link anh
                baiviet.setImg(downloadUri.toString());

            });

            Glide.with(this)
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imganhbaiviet);        }
    }


    private void anhxa() {
        toolbar = findViewById(R.id.tbtaobaiviet);
        imganhbaiviet = findViewById(R.id.imganhbaiviet);
        imgavatartbv = findViewById(R.id.imgavatartbv);
        txtnametbv = findViewById(R.id.txtnametbv);
        btndangtai = findViewById(R.id.btndangtai);
        edtbaibnvt = findViewById(R.id.edtbaibnvttbv);
        edttitletbv = findViewById(R.id.txttitletbv);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

    }
}