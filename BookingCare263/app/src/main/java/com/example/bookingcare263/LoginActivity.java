package com.example.bookingcare263;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingcare263.ui.adminui.AdminActivity;
import com.example.bookingcare263.ui.uiuser.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText edtsdtlogin, edtpasslogin;

    private Button btnlogin;
    private TextView tosigup;
    private TextView txtquenmk;

    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        anhxa();
        auth = FirebaseAuth.getInstance();
        edtpasslogin.setOnEditorActionListener((v, actionId, event) -> {
                    hideKeyboard();
                    return false;
                });
        btnlogin.setOnClickListener(e->login());
        tosigup.setOnClickListener(e->startActivity(new Intent(LoginActivity.this, SignUpActitvity.class)));


        txtquenmk.setOnClickListener(e->{
            AlertDialog builder = new AlertDialog.Builder(LoginActivity.this).create();
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_fotgotpassword, null);
            EditText  edtemailforgot = view.findViewById(R.id.edtemailforgot);
            Button btncancel = view.findViewById(R.id.btncancel);
            Button btnforgot = view.findViewById(R.id.btnforgot);
            builder.setView(view);

            btncancel.setOnClickListener(v->{
                builder.dismiss();
            });

            btnforgot.setOnClickListener(v->{
                checkemail(edtemailforgot);
                builder.dismiss();
            });

            if(builder.getWindow()!= null){
                builder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
            builder.show();

        });

        // cap nhap mat khau moi len realtime neu có


    }


    private void checkemail(EditText email){
        if(email.getText().toString().trim().isEmpty()){
            email.setError("Vui lòng nhập số điện thoại");
            email.requestFocus();
            return ;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()){
            email.setError("Vui lòng nhập đúng định dạng emial");
            email.requestFocus();
            return ;
        }

        auth.sendPasswordResetEmail(email.getText().toString().trim()).addOnCompleteListener(task->{
            if(task.isSuccessful()){
                Toast.makeText(LoginActivity.this, "Đã gửi email thành công", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void login() {
        String sdt = edtsdtlogin.getText().toString().trim();
        String pass = edtpasslogin.getText().toString().trim();

        if(!validate(sdt, pass)) return ;

        reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkuser = reference.orderByChild("phone").equalTo(sdt);

        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String passfromdb = ds.child("pass").getValue(String.class);
                        if(passfromdb!=null && passfromdb.equals(pass)){
                            // lấy role
                            String asdb = ds.child("as").getValue(String.class);
                            if(asdb!=null && asdb.equals("user")){
                                String name = ds.child("name").getValue(String.class);
                                String phone = ds.child("phone").getValue(String.class);

                                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                                intent.putExtra("iduser", sdt);
                                intent.putExtra("name", name);
                                intent.putExtra("phone", phone);
                                startActivity(intent);

                            } else {
                                Toast.makeText(LoginActivity.this, "Iam admin", LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                intent.putExtra("role", "admin");
                                startActivity(intent);
                            }


                        }
                    }
                } else{
                    Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại", LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(edtpasslogin.getWindowToken(), 0);
        }
    }

    private boolean validate (String sdt, String pass){

        if(sdt.isEmpty()){
            edtsdtlogin.setError("Vui lòng nhập số điện thoại");
            edtsdtlogin.requestFocus();
            return false;
        }
        if (pass.isEmpty()){
            edtpasslogin.setError("Vui lòng nhập mật khẩu");
            edtpasslogin.requestFocus();
            return false;
        }
        return true;

    }

    private void anhxa() {
        edtsdtlogin = findViewById(R.id.edtsdtlogin);
        edtpasslogin = findViewById(R.id.edtpasslogin);
        btnlogin = findViewById(R.id.btnlogin);
        tosigup = findViewById(R.id.tosigup);
        txtquenmk = findViewById(R.id.txtquenmk);

    }
}