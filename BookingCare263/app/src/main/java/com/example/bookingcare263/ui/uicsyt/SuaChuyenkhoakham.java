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
import com.example.bookingcare263.ui.Xuly;

public class SuaChuyenkhoakham extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgsuackkham;
    EditText edtsuatenckkhoa, edtsuadiachikhamck, edtsuagiakhamck, edtsuathongtinkham;
    Chuyenkhoacsyt ck;
    Uri imageUri;
    Button btnluuttcksua;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_chuyenkhoakham);

        anhxa();

        imgsuackkham.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });
        btnluuttcksua.setOnClickListener(e->{
            ck.setTenkhoa(edtsuatenckkhoa.getText().toString());
            ck.setDiachi(edtsuadiachikhamck.getText().toString());
            ck.setGiakham(edtsuagiakhamck.getText().toString());
            ck.setThongtin(edtsuathongtinkham.getText().toString());
            FirebaseHelper.updatechuyenkhoaCSYT(ck, new FirebaseCallBack() {
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
            Glide.with(imgsuackkham.getContext())
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgsuackkham);        }
    }


    private void anhxa() {
        toolbar = findViewById(R.id.tbsuacsytck);
        imgsuackkham = findViewById(R.id.imgckkham);
        edtsuatenckkhoa = findViewById(R.id.edtsuatenckkhoa);
        edtsuadiachikhamck = findViewById(R.id.edtsuadiachikhamck);
        edtsuagiakhamck = findViewById(R.id.edtsuagiakhamck);
        edtsuathongtinkham = findViewById(R.id.edtsuathongtinkham);
        btnluuttcksua = findViewById(R.id.btnluuttcksua);
        Intent intent = getIntent();
        ck = (Chuyenkhoacsyt) intent.getSerializableExtra("chuyenkhoa");
        edtsuatenckkhoa.setText(ck.getTenkhoa());
        edtsuadiachikhamck.setText(ck.getDiachi());
    }
}