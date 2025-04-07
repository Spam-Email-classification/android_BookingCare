package com.example.bookingcare263;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.ui.bacsiui.ThongtinBacsi;
import com.example.bookingcare263.ui.uiuser.ThongtinUser;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingcare263.databinding.ActivityUserBinding;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityUserBinding binding;

    public static  String iduser ;
    public static String phoneuser;
    public static String roleuser;



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




        Intent intent = getIntent();
        iduser = intent.getStringExtra("iduser");
        String name = intent.getStringExtra("name");
        phoneuser = intent.getStringExtra("phone");
        roleuser = intent.getStringExtra("role");

        Toast.makeText(this, "iduser" + iduser, Toast.LENGTH_SHORT).show();

        NavigationView nav_view = findViewById(R.id.nav_view);
        View headerView = nav_view.getHeaderView(0);
        TextView txtnameheader = headerView.findViewById(R.id.txtnameheader);
        TextView txtsdtheader = headerView.findViewById(R.id.txtsdtheader);
        ImageView imgheader = headerView.findViewById(R.id.imgheader);

        if(name != null && phoneuser != null){
            txtnameheader.setText(name);
            txtsdtheader.setText(phoneuser);
        }






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
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}