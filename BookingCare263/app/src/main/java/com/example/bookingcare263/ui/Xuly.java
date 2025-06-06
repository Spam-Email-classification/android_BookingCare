package com.example.bookingcare263.ui;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Xuly {

    public static Uri copyImageToInternalStorage(Context context, Uri uri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            if (inputStream == null) {
                Toast.makeText(context, "Không thể mở InputStream", Toast.LENGTH_SHORT).show();
                return null;
            }

            // Tạo một file với tên duy nhất để tránh ghi đè
            File imageDir = new File(context.getFilesDir(), "images");
            if (!imageDir.exists()) {
                imageDir.mkdir();
            }
            File tempFile = new File(imageDir, "image_" + System.currentTimeMillis() + ".jpg");

            OutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();

            Uri fileUri = Uri.fromFile(tempFile);
            return fileUri;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Lỗi khi sao chép ảnh", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static void uploadImageToFirebaseStorage(Context context, Uri imageUri, String imageName, OnSuccessListener<Uri> onSuccessListener) {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference("images/" + imageName);

        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    storageRef.getDownloadUrl().addOnSuccessListener(onSuccessListener);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Lỗi khi tải ảnh lên Firebase", Toast.LENGTH_SHORT).show();
                });
    }


    public static String getRelativeTime(String timestampStr) {
        if (timestampStr == null || timestampStr.isEmpty()) {
            return "Không rõ thời gian"; // Hoặc để trống tùy bạn
        }

        try {
            long timestamp = Long.parseLong(timestampStr);
            long currentTime = System.currentTimeMillis();
            long diffMillis = currentTime - timestamp;

            long seconds = diffMillis / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;

            if (seconds < 60) {
                return "Vừa xong";
            } else if (minutes < 60) {
                return minutes + " phút trước";
            } else if (hours < 24) {
                return hours + " giờ trước";
            } else if (days < 7) {
                return days + " ngày trước";
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                return sdf.format(new Date(timestamp));
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Thời gian không hợp lệ";
        }
    }




}
