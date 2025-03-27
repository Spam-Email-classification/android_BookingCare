package com.example.bookingcare263;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookingcare263.model.accout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActitvity extends AppCompatActivity {
    EditText edtnamesignup, edtsdtsignup, edtpasssignup;
    Button btnsigup;
    TextView tologin;
    DatabaseReference reference;
    DatabaseHelper helper;

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

        btnsigup.setOnClickListener(e->signuprealtime());

        tologin.setOnClickListener(e->startActivity(new Intent(SignUpActitvity.this, LoginActivity.class)));


    }

    private void signuprealtime() {
        reference = FirebaseDatabase.getInstance().getReference("Users");

        String name = edtnamesignup.getText().toString().trim();
        String sdt = edtsdtsignup.getText().toString().trim();
        String pass = edtpasssignup.getText().toString().trim();
        String as = "user";

        accout acc = new accout(name, sdt, pass, as);
        reference.child(sdt).setValue(acc);
//        helper = new DatabaseHelper(this);
//        helper.addAccount(acc);

        edtnamesignup.setText("");
        edtsdtsignup.setText("");
        edtpasssignup.setText("");

        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(SignUpActitvity.this, LoginActivity.class));
    }
}