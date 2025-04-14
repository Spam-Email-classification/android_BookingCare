package com.example.bookingcare263;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.ui.bacsiui.ThongtinBacsi;
import com.example.bookingcare263.ui.uicsyt.ThongtinCSYT;
import com.example.bookingcare263.ui.uiuser.ThongtinUser;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingcare263.databinding.ActivityUserBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import android.Manifest;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class UserActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityUserBinding binding;

    public static  String iduser ;
    public static String phoneuser;
    public static String roleuser = null;

    private static final int REQUEST_CAMERA_PERMISSION = 100; // Mã yêu cầu quyền, có thể thay đổi




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarUser.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_lichhen, R.id.nav_lienhe, R.id.nav_dangxuat)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_user);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_dangxuat) {
                    // Xử lý đăng xuất
                    UserActivity.iduser = null;
                    Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    return true;
                }
                // Xử lý các mục khác nếu có
                boolean handled = NavigationUI.onNavDestinationSelected(item, navController);

                // Đóng Navigation Drawer sau khi chọn mục
                drawer.closeDrawer(GravityCompat.START);
                return handled;
            }


        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        1001);
            }
        }


        Intent intent = getIntent();
        iduser = intent.getStringExtra("iduser");
        String name = intent.getStringExtra("name");
        phoneuser = intent.getStringExtra("phone");
        roleuser = intent.getStringExtra("role");


        NavigationView nav_view = findViewById(R.id.nav_view);
        View headerView = nav_view.getHeaderView(0);
        TextView txtnameheader = headerView.findViewById(R.id.txtnameheader);
        TextView txtsdtheader = headerView.findViewById(R.id.txtsdtheader);
        ImageView imgheader = headerView.findViewById(R.id.imgheader);

        if(name != null && phoneuser != null){
            txtnameheader.setText(name);
            txtsdtheader.setText(phoneuser);
        }


        if (iduser != null && !iduser.isEmpty()) {
            getFCMToken();
        } else {
            Log.w("FCM", "iduser is null or empty. Cannot get FCM token.");
        }

        // Kiểm tra xem quyền đã được cấp chưa
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu quyền camera
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }


    }

    void sendTokenToServer(String token) {
        String url = "https://sendnotification-4iegnseetq-uc.a.run.app"; // Thay thế bằng URL API của bạn
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý phản hồi từ server
                        Log.d("FCM", "Token sent to server: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            String errorMessage = new String(error.networkResponse.data);
                            Log.e("FCM", "Error code: " + statusCode);
                            Log.e("FCM", "Response Error: " + errorMessage);
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("token", token);  // Kiểm tra xem token có phải là chuỗi hợp lệ không

                // Nếu API yêu cầu thêm các tham số khác, hãy thêm vào đây:
                // params.put("user_id", userId);
                // params.put("other_param", otherValue);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền được cấp, mở camera

            } else {
                // Quyền bị từ chối, hiển thị thông báo hoặc thực hiện hành động khác
                Toast.makeText(this, "Camera permission is required to take a picture", Toast.LENGTH_SHORT).show();
            }
        }
    }




    void getFCMToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String token = task.getResult();
                // Cập nhật token lên Realtime Database
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                ref.child(iduser).child("token").setValue(token)
                        .addOnCompleteListener(databaseTask -> {
                            if (databaseTask.isSuccessful()) {
                                Log.d("FCM", "FCM token saved successfully for user: " + iduser);
                                // Gửi token đến server sau khi lưu vào Realtime Database thành công
                                sendTokenToServer(token);
                            } else {
                                Log.e("FCM", "Failed to save FCM token for user: " + iduser, databaseTask.getException());
                            }
                        });
            } else {
                Log.e("FCM", "Failed to get FCM token", task.getException());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_user);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.account) {
            if (UserActivity.iduser == null) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else if(UserActivity.roleuser.equals("user")){

                Intent intent = new Intent(UserActivity.this, ThongtinUser.class);
                startActivity(intent);
            } else if(UserActivity.roleuser.equals("bacsi")){
                Intent intent = new Intent(UserActivity.this, ThongtinBacsi.class);

                startActivity(intent);
            } else if( UserActivity.roleuser.equals("csyt")){


                FirebaseHelper.getcsytbyid(UserActivity.iduser, new FirebaseCallBack<Cosoyte>() {
                    @Override
                    public void onSuccess(Cosoyte data) {
                        Intent intent = new Intent(UserActivity.this, ThongtinCSYT.class);
                        intent.putExtra("cosoyte", data);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}