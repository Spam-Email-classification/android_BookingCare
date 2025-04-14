package com.example.bookingcare263.ui.bacsiui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.R;

public class ImageActivity extends AppCompatActivity {
    ImageView imgx, imggiayphepdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image);
        imgx = findViewById(R.id.imgx);
        imggiayphepdetail = findViewById(R.id.imggiayphepdetail);

        imgx.setOnClickListener(e->{
            finish();
        });
        Intent intent = getIntent();
        String img = intent.getStringExtra("anhgiayphep");
        if(img != null)
            Glide.with(imggiayphepdetail.getContext())
                    .load(Uri.parse(img)) // Chuyển String thành Uri
                    .placeholder(R.drawable.imagechose) // Ảnh mặc định nếu đang load
                    .error(R.drawable.imagechose) // Ảnh mặc định nếu load thất bại
                    .into(imggiayphepdetail);
        else{
            Log.d("TAG", "onCreate: khong co anh");
        }

    }
}