package com.example.bookingcare263.ui.uicsyt;

import static com.example.bookingcare263.ui.Xuly.uploadImageToFirebaseStorage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Chuyenkhoacsyt;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.ui.Xuly;

public class Addckcsyt extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgckkham;
    EditText edttenckkhoa, edtdiachikhamck, edtgiakhamck, edtthongtinkham;
    Chuyenkhoacsyt ck;
    Uri imageUri;
    Button  btnluuttck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addckcsyt);

        anhxa();
        Intent intent = getIntent();
        Cosoyte csyt = (Cosoyte) intent.getSerializableExtra("cosoyte");
        ck.setSdtcsyt(csyt.getSdt());
        imgckkham.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });

        btnluuttck.setOnClickListener(v->{
            ck.setTenkhoa(edttenckkhoa.getText().toString());
            ck.setDiachi(edtdiachikhamck.getText().toString());
            ck.setGiakham(edtgiakhamck.getText().toString());
            ck.setThongtin(edtthongtinkham.getText().toString());
            if(imageUri == null){
                ck.setImg("");
            }

            FirebaseHelper.addchuyenkhoaCSYT(ck, new FirebaseCallBack() {
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = Xuly.copyImageToInternalStorage(this, imageUri);
            String uniqueImageName = "image_" + System.currentTimeMillis() + ".jpg";

            uploadImageToFirebaseStorage(this, imageUri, uniqueImageName, downloadUri -> {
                // upload link anh
                ck.setImg(downloadUri.toString());
            });
            // Lấy URI của ảnh
            Glide.with(imgckkham.getContext())
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgckkham);        }
    }


    private void anhxa() {
        toolbar = findViewById(R.id.tbaddcsytck);
        imgckkham = findViewById(R.id.imgaddkkham);
        edttenckkhoa = findViewById(R.id.tenckkhoa);
        edtdiachikhamck = findViewById(R.id.edtdiachikhamck);
        edtgiakhamck = findViewById(R.id.edtgiakhamck);
        edtthongtinkham = findViewById(R.id.edtthongtinkham);
        btnluuttck = findViewById(R.id.btnluuttck);
        ck = new Chuyenkhoacsyt();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            finish();
        });


    }
}