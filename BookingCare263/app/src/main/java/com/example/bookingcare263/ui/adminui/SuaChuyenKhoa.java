package com.example.bookingcare263.ui.adminui;

import static com.example.bookingcare263.ui.Xuly.uploadImageToFirebaseStorage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.UserActivity;
import com.example.bookingcare263.model.chuyenkhoa;
import com.example.bookingcare263.ui.Xuly;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuaChuyenKhoa extends AppCompatActivity {

    Toolbar tbSuachuyenkhoa;
    ImageView imgavatarsuachuyenkhoa;
    EditText edttensuachuyenkhoa, edtthongtinsuachuyenkhoa;
    Button btnSuaChuyenKhoa;
    chuyenkhoa ck;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_chuyen_khoa);
        anhxa();
        Intent intent = getIntent();
        ck = (chuyenkhoa) intent.getSerializableExtra("chuyen khoa");
        edttensuachuyenkhoa.setText(ck.getTenchuyenkhoa());
        edtthongtinsuachuyenkhoa.setText(ck.getThongtin());
        Glide.with(this)
                .load(Uri.parse(ck.getImg())) // Chuyển String thành Uri
                .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                .into(imgavatarsuachuyenkhoa);

        imgavatarsuachuyenkhoa.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });
        btnSuaChuyenKhoa.setOnClickListener(e->{
                    String tenchuyenkhoa = edttensuachuyenkhoa.getText().toString();
                    String thongtin = edtthongtinsuachuyenkhoa.getText().toString();
                    String avatar ;
                    if(imageUri== null){
                        avatar = ck.getImg();

                    } else {
                        avatar = imageUri.toString();
                    }
                    chuyenkhoa ck1 = new chuyenkhoa(ck.getId(), tenchuyenkhoa, avatar, thongtin);
                    FirebaseHelper.updatechuyenkhoa(ck1, new FirebaseCallBack() {
                        @Override
                        public void onSuccess(Object data) {

                        }

                        @Override
                        public void onFailed(String message) {

                        }
                    });
                    finish();
                }
        );
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageUri = Xuly.copyImageToInternalStorage(this, imageUri);// Lấy URI của ảnh
            String uniquename = "image_" + System.currentTimeMillis() + ".jpg";
            uploadImageToFirebaseStorage(this, imageUri, uniquename, downloadUri -> {
                // upload link anh
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_chuyenkhoa");
                ref.child(UserActivity.iduser).child("img").setValue(downloadUri.toString());

            });

            Glide.with(this)
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgavatarsuachuyenkhoa);        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menuxoa, menu);

        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.delete) {
            // add
//            helper.deleteChuyenKhoa(ck.getId());
            FirebaseHelper.deletechuyenkhoa(ck.getId());
            finish();


        }


        return super.onOptionsItemSelected(item);
    }




    private void anhxa() {
        tbSuachuyenkhoa = findViewById(R.id.tbSuachuyenkhoa);
        imgavatarsuachuyenkhoa = findViewById(R.id.imgavatarsuachuyenkhoa);
        edttensuachuyenkhoa = findViewById(R.id.edttensuachuyenkhoa);
        edtthongtinsuachuyenkhoa = findViewById(R.id.edtthongtinsuachuyenkhoa);
        btnSuaChuyenKhoa = findViewById(R.id.btnSuasuacuye);



        setSupportActionBar(tbSuachuyenkhoa);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbSuachuyenkhoa.setNavigationOnClickListener(v -> onBackPressed());

    }
}