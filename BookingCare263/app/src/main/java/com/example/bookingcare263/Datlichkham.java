package com.example.bookingcare263;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.bumptech.glide.request.RequestOptions;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.lichhen;

import java.io.File;

public class Datlichkham extends AppCompatActivity {
    private TextView txtDescription;
    private TextView txtSeeMore, txtTime, txtAddress, txtNameBs, txtchuyenkhoa, txtgiakhamdk, txtgiakham, txttong;
    private Button btndatlichkham;
    ImageView imgavtars;
    EditText edthoten, edtsdt, edtngay, edtdiachi;

    DatabaseHelper dbhelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datlichkham);

        anhxa();

        Intent intent = getIntent();
        dbhelper = new DatabaseHelper(this);

        Bacsi bacsi = (Bacsi) intent.getSerializableExtra("bacsi");
        txtDescription.setText(bacsi.getThongtin());
        txtAddress.setText(bacsi.getDiachi());
        txtNameBs.setText(bacsi.getName());
        txtchuyenkhoa.setText(bacsi.getChuyenkhoa());
        txtgiakhamdk.setText(String.valueOf(bacsi.getGiaKham()));
        txtgiakham.setText(String.valueOf(bacsi.getGiaKham()));
        txttong.setText(String.valueOf(bacsi.getGiaKham()));

        String anh = intent.getStringExtra("anh");
        Glide.with(this)
                .load(new File(this.getFilesDir(), anh))
                .apply(RequestOptions.circleCropTransform())
                .into(imgavtars);
        Log.d("anh ", anh);
        btndatlichkham.setOnClickListener(e->{
            String iduser = UserActivity.iduser;
            if(!validate()) return;

            lichhen lh = new lichhen(
                    iduser,
                    bacsi.getId(),
                    edtngay.getText().toString(),
                    txtTime.getText().toString(),
                    "Đang chờ",
                    edthoten.getText().toString(),
                    edtsdt.getText().toString(),
                    edtdiachi.getText().toString(),
                    anh
            );
            boolean inserted = dbhelper.addlichhen(lh);
            if(inserted){
                Log.d("DatabaseHelper", "✅ Đã thêm lịch hẹn thành công!");
            } else{
                Log.d("DatabaseHelper", "❌ Thêm lịch hẹn thất bại!");
            }


            finish();

        });




    }

    private boolean validate(){
        String hoten = edthoten.getText().toString();
        String sdt = edtsdt.getText().toString();
        String ngay = edtngay.getText().toString();
        String diachi = edtdiachi.getText().toString();
        if (hoten.isEmpty()){
            edthoten.setError("Vui lòng nhập họ tên");
            return false;
        }
        if (sdt.isEmpty()){
            edtsdt.setError("Vui lòng nhập số điện thoại");
            return false;
        }
        if (ngay.isEmpty()){
            edtngay.setError("Vui lòng nhập ngày");
            return false;
        }
        if (diachi.isEmpty()){
            edtdiachi.setError("Vui lòng nhập địa chỉ");
            return false;
        }
        else {
            return true;
        }
    }

    private void anhxa() {
        txtDescription = findViewById(R.id.txtthongtin);
        txtSeeMore = findViewById(R.id.btmxemthem);
        txtTime = findViewById(R.id.txttime);
        txtAddress = findViewById(R.id.txtdiachi);
        txtNameBs = findViewById(R.id.txtnamebs);
        txtchuyenkhoa = findViewById(R.id.txtchuyenkhoa1);
        txtgiakhamdk = findViewById(R.id.txtgiakhamdk);
        txtgiakham = findViewById(R.id.txtgiakham);
        txttong = findViewById(R.id.txttong);
        btndatlichkham = findViewById(R.id.btnxnkham);
        imgavtars = findViewById(R.id.imgavtarbsdt2);
        edthoten = findViewById(R.id.edthoten);
        edtsdt = findViewById(R.id.edtsdt);
        edtngay = findViewById(R.id.edtngay);
        edtdiachi = findViewById(R.id.textView15);


        txtSeeMore.setOnClickListener(v -> {
            if (txtDescription.getMaxLines() == 2) {
                txtDescription.setMaxLines(Integer.MAX_VALUE); // Hiển thị toàn bộ nội dung
                txtSeeMore.setText("Thu gọn");
            } else {
                txtDescription.setMaxLines(2); // Giới hạn lại
                txtSeeMore.setText("Xem thêm");
            }
        });


    }
}