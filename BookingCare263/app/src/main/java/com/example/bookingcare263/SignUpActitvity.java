package com.example.bookingcare263;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.model.benhnhan;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;


public class SignUpActitvity extends AppCompatActivity {
    EditText edtnamesignup, edtsdtsignup, edtpasssignup;
    Button btnsigup;
    TextView tologin;
    DatabaseReference reference;
    Spinner spinrole;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    FirebaseAuth mAuth;
    String storedVerificationId;

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
        String role[] = { "bệnh nhân", "bác sĩ", "cơ sở y tế"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, role);
        spinrole.setAdapter(adapter);
        spinrole.setSelection(0);
        mAuth = FirebaseAuth.getInstance();



        btnsigup.setOnClickListener(e->{
//            String sdt = edtsdtsignup.getText().toString().trim();
//            if(sdt.length() !=10 || !sdt.startsWith("0")){
//                edtsdtsignup.setError("Vui lòng nhập số điện thoại hợp lệ");
//                edtsdtsignup.requestFocus();
//            }
//            sendOtp(sdt);
            signuprealtime();
        });

        tologin.setOnClickListener(e->startActivity(new Intent(SignUpActitvity.this, LoginActivity.class)));

    }

    private void sendOtp(String sdt) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84" + sdt.substring(1),
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        // Tự động xác minh (nếu được) không cần nhập OTP
                        signInWithPhoneCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(SignUpActitvity.this, "Xác minh thất bại: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("OTP", "Verification failed", e);
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        super.onCodeSent(verificationId, token);
                        storedVerificationId = verificationId;
                        resendingToken = token;
                        showOtpDialog(verificationId); // ✅ Gọi dialog nhập OTP ở đây
                    }
                }
        );
    }

    private void signInWithPhoneCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Đúng mã OTP → thực hiện lưu dữ liệu người dùng
                        signuprealtime(); // hoặc hàm bạn dùng để lưu tài khoản
                    } else {
                        Toast.makeText(this,
                                "Xác minh OTP thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void showOtpDialog(String verificationId){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập mã OTP");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setHint("Mã OTP gồm 6 chữ số");
        input.setPadding(50, 40, 50, 10);
        builder.setView(input);

        builder.setPositiveButton("Xác minh", (dialog, which) -> {
            String otp = input.getText().toString().trim();
            if (!otp.isEmpty()) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                signInWithPhoneCredential(credential);
            } else {
                Toast.makeText(this, "Vui lòng nhập mã OTP", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        builder.show();
    }



    private void signuprealtime() {
        reference = FirebaseDatabase.getInstance().getReference("Users");

        String name = edtnamesignup.getText().toString().trim();
        String sdt = edtsdtsignup.getText().toString().trim();
        String pass = edtpasssignup.getText().toString().trim();
        String as = "user";
        String status = "Đang hoạt động";
        String banla = spinrole.getSelectedItem().toString();

        if (banla.equals("bệnh nhân")) {
            as = "user";
            status = "Đang hoạt động";

        } else if(banla.equals("bác sĩ")) {
            as = "bacsi";
            status = "Chờ duyệt";
        } else {
            as = "csyt";
            status = "Đang hoạt động";

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

        String token = "";

        accout acc = new accout(name, sdt, pass, as, status, token);
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
                        } else if (acc.getAs().equals("bacsi")) {
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

                        } else{
                            Cosoyte csyt = new Cosoyte(name, "", "", "", "", "", "", sdt, "");
                            FirebaseHelper.insertcosoyte(csyt, new FirebaseCallBack() {
                                @Override
                                public void onSuccess(Object data) {

                                }

                                @Override
                                public void onFailed(String message) {

                                }
                            });


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