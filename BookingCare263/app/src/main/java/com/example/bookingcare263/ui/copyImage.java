package com.example.bookingcare263.ui;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class copyImage {

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



}
