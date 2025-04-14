package com.example.bookingcare263.ui.bacsiui;

import static com.example.bookingcare263.ui.Xuly.uploadImageToFirebaseStorage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.ui.Xuly;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuaBS extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imgavatr4csytsua;
    private TextView edtten4sua, edtsdtsua4;
    private EditText  edtemailsua4, edtdiachisua, edtmasogiayphepsua, edtgiakhamsua, edtthongtin4sua;
    private Button btnsua4csyt;
    private Spinner spinchuyenkhoacsytsua;
    Bacsi bacsi;

    Uri imageUri;

    String [] items = {"Cơ xương khớp", "Thần kinh", "Tiêu hóa", "Tim mạch", "Cột sống", "Y học cổ truyền", "Châm cứu", "Sản phụ khoa", "Da liễu", "Hô hấp phổi", "Chuyên khoa mắt", "Thân - Tiết niệu", "Ung bướu" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua_bs);


        anhxa();

        Intent intent = getIntent();
        bacsi = (Bacsi) intent.getSerializableExtra("bacsi");

        edtten4sua.setText(bacsi.getName());
        edtsdtsua4.setText(bacsi.getSdt());
        edtemailsua4.setText(bacsi.getEmail());
        edtdiachisua.setText(bacsi.getDiachi());
        edtmasogiayphepsua.setText(bacsi.getSogiayphephanhnghe());
        edtgiakhamsua.setText(bacsi.getGiaKham());
        edtthongtin4sua.setText(bacsi.getThongtin());
        // chuyen khoa luu duoi dang String (

        String chuyenkhoa = bacsi.getChuyenkhoa();
        int index = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(chuyenkhoa)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            final int selectedIndex = index;
            spinchuyenkhoacsytsua.post(() -> spinchuyenkhoacsytsua.setSelection(selectedIndex));
        }

        if ( bacsi.getImg() != null && !bacsi.getImg().isEmpty()){
        Glide.with(imgavatr4csytsua.getContext())
                .load(Uri.parse(bacsi.getImg())) // Chuyển String thành Uri
                .circleCrop()
                .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                .into(imgavatr4csytsua);}
        else {
            imgavatr4csytsua.setImageResource(R.drawable.baseline_account_circle_24);
        }

        imgavatr4csytsua.setOnClickListener(view -> {
            // chon anh tu dien thoai
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setType("image/*");
            startActivityForResult(intent1, 1);

        });


        btnsua4csyt.setOnClickListener(e->{
            bacsi.setName(edtten4sua.getText().toString());
            bacsi.setSdt(edtsdtsua4.getText().toString());
            bacsi.setEmail(edtemailsua4.getText().toString());
            bacsi.setDiachi(edtdiachisua.getText().toString());
            bacsi.setSogiayphephanhnghe(edtmasogiayphepsua.getText().toString());
            bacsi.setGiaKham(edtgiakhamsua.getText().toString());
            bacsi.setThongtin(edtthongtin4sua.getText().toString());
            bacsi.setChuyenkhoa(spinchuyenkhoacsytsua.getSelectedItem().toString());
            if (imageUri != null) {
                bacsi.setImg(imageUri.toString());
            }

            FirebaseHelper.updateBacsi(bacsi, new FirebaseCallBack() {
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
            String uniqueImageName = "image_" + System.currentTimeMillis() + ".jpg";
            uploadImageToFirebaseStorage(this, imageUri, uniqueImageName, downloadUri -> {

                // upload link anh
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
                ref.child(bacsi.getSdt()).child("img").setValue(downloadUri.toString());

            });
            Glide.with(imgavatr4csytsua.getContext())
                    .load(Uri.parse(imageUri.toString())) // Chuyển String thành Uri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgavatr4csytsua);

        }
    }


    private void anhxa() {
        toolbar = findViewById(R.id.tbSuacbnv);
        imgavatr4csytsua = findViewById(R.id.imgavatar4sua);
        edtten4sua = findViewById(R.id.edtten4sua);
        edtsdtsua4 = findViewById(R.id.edtsdtsua4);
        edtemailsua4 = findViewById(R.id.edtemailsua4);
        edtdiachisua = findViewById(R.id.edtdiachisua);
        spinchuyenkhoacsytsua = findViewById(R.id.spinckbssua);
        edtmasogiayphepsua = findViewById(R.id.edtmasogiayphepsua);
        edtgiakhamsua = findViewById(R.id.edtgiakhamsua);
        edtthongtin4sua = findViewById(R.id.edtthongtin4sua);
        btnsua4csyt = findViewById(R.id.btnSua4);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinchuyenkhoacsytsua.setAdapter(adapter1);






    }
}