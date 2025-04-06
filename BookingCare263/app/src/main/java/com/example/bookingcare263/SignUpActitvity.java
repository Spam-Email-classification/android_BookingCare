package com.example.bookingcare263;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.model.benhnhan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActitvity extends AppCompatActivity {
    EditText edtnamesignup, edtsdtsignup, edtpasssignup;
    Button btnsigup;
    TextView tologin;
    DatabaseReference reference;
    DatabaseHelper helper;
    Spinner spinrole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.actitvity_signup);

        // anh xa
        edtnamesignup = findViewById(R.id.edtnamesignup);
        edtsdtsignup = findViewById(R.id.edtsdtsignup);
        edtpasssignup = findViewById(R.id.edtpasssignup);
        btnsigup = findViewById(R.id.btnsigup);
        tologin = findViewById(R.id.tologin);
        spinrole = findViewById(R.id.spinrole);
        String role[] = { "benh nhan", "bacsi"};
        helper = new DatabaseHelper(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, role);
        spinrole.setAdapter(adapter);
        spinrole.setSelection(0);



        btnsigup.setOnClickListener(e->signuprealtime());

        tologin.setOnClickListener(e->startActivity(new Intent(SignUpActitvity.this, LoginActivity.class)));


    }

    private void signuprealtime() {
        reference = FirebaseDatabase.getInstance().getReference("Users");

        String name = edtnamesignup.getText().toString().trim();
        String sdt = edtsdtsignup.getText().toString().trim();
        String pass = edtpasssignup.getText().toString().trim();
        String as = "user";
        String status = "Đang hoạt động";
        String banla = spinrole.getSelectedItem().toString();

        if (banla.equals("benh nhan")) {
            as = "user";
            status = "Đang hoạt động";

        } else {
            as = "bacsi";
            status = "Chờ duyệt";
        }

        // Kiểm tra dữ liệu trước khi đăng ký
        if (name.isEmpty()) {
            edtnamesignup.setError("Please enter name");
            return;
        }
        if (sdt.isEmpty()) {
            edtsdtsignup.setError("Please enter phone number");
            return;
        }

        if (pass.isEmpty()) {
            edtpasssignup.setError("Please enter password");
            return;
        }
        if (pass.length() < 6) {
            edtpasssignup.setError("Password must be at least 6 characters");
            return;
        }

        accout acc = new accout(name, sdt, pass, as, status);

                FirebaseHelper.addAccout(acc, new FirebaseCallBack<accout>(){
                    @Override
                    public void onSuccess(accout data) {
                        // Them 1 bac si hoac benh nhan dựa  trên role của accout (them len fire base)
                        if(acc.getAs().equals("user")){
                            benhnhan bn = new benhnhan(name, sdt, "", "", "", "", "");
                            FirebaseHelper.addbenhnhan(bn, new FirebaseCallBack(){
                                @Override
                                public void onSuccess(Object data) {
                                }
                                @Override
                                public void onFailed(String message) {
                                }

                            });
                        } else {
                            Bacsi bs = new Bacsi( name,  "", "", "", "", "", "", "",sdt);
                            FirebaseHelper.addBacsi(bs, new FirebaseCallBack() {
                                        @Override
                                        public void onSuccess(Object data) {
                                        }

                                        @Override
                                        public void onFailed(String message) {
                                        }
                                    }
                                );

                        }
                        Toast.makeText(SignUpActitvity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActitvity.this, LoginActivity.class));
                        finish();
                    }
                    @Override
                    public void onFailed(String message) {
                        Toast.makeText(SignUpActitvity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                });




    }
}