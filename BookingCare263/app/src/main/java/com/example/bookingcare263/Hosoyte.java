package com.example.bookingcare263;

import static com.example.bookingcare263.ui.uiuser.UserActivity.phoneuser;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookingcare263.model.benhnhan;

public class Hosoyte extends AppCompatActivity {
    Toolbar tbhsyt;
    ImageView imghsyt;
    EditText edthotencsyt, edtsdthsyt, edtdiachiahsyt, edtngaysinhhsyt, edtbenhlyne;

    Button btnluuhsyt;
    RadioGroup rdgioitinh;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hosoyte);
        anhxa();
        benhnhan bn1 = helper.getbenhnhan(phoneuser);
        edthotencsyt.setText(bn1.getTen());
        edtsdthsyt.setText(bn1.getSodienthoai());
        edtdiachiahsyt.setText(bn1.getDiachi());
        edtngaysinhhsyt.setText(bn1.getNgaysinh());
        edtbenhlyne.setText(bn1.getBenhlynen());

        if(bn1.getGioitinh()!= null &&  bn1.getGioitinh().equals("Nam")){
            rdgioitinh.check(R.id.rdbtnnam);
        }else{
            rdgioitinh.check(R.id.rdbtnnu);
        }


        btnluuhsyt.setOnClickListener(e->{
            String ten = edthotencsyt.getText().toString();
            String sdt = edtsdthsyt.getText().toString();
            String diachi = edtdiachiahsyt.getText().toString();
            String ngaysinh = edtngaysinhhsyt.getText().toString();
            String benhlynen = edtbenhlyne.getText().toString();
            // get gioi tinh
            int selectedId = rdgioitinh.getCheckedRadioButtonId();
            String gioitinh = "";
            if (selectedId == R.id.rdbtnnam) {
                gioitinh = "Nam";
            } else if (selectedId == R.id.rdbtnnu) {
                gioitinh = "Nữ";
            }
            String avatar = "";
            benhnhan bn = new benhnhan(ten, sdt, diachi, gioitinh, ngaysinh, benhlynen, avatar);
            helper.updatebenhnha(bn);
            finish();
        });

    }

    private void anhxa() {
        tbhsyt = findViewById(R.id.tbhsyt);
        imghsyt = findViewById(R.id.imghsyt);
        edthotencsyt = findViewById(R.id.edthotencsyt);
        edtsdthsyt = findViewById(R.id.edtsdthsyt);
        edtdiachiahsyt = findViewById(R.id.edtdiachiahsyt);
        edtngaysinhhsyt = findViewById(R.id.edtngaysinhhsyt);
        edtbenhlyne = findViewById(R.id.edtbenhlyne);
        btnluuhsyt = findViewById(R.id.btnluuhsyt);
        rdgioitinh = findViewById(R.id.rdgioitinh);
        helper = new DatabaseHelper(this);


        int selectedId = rdgioitinh.getCheckedRadioButtonId();




    }
}