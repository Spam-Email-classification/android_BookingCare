package com.example.bookingcare263;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.lichhen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "BOOKCARE.db"; // Tên database
    private static final int DATABASE_VERSION = 1; // Phiên bản database
    private static final String DB_PATH_SUFFIX = "/databases/";
    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        copyDatabaseFromAssets();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +"tbbacsi");
        onCreate(db);
    }

    public boolean addBacsi(Bacsi bacsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", bacsi.getName());
        values.put("chuyenkhoa", bacsi.getChuyenkhoa());
        values.put("diachi", bacsi.getDiachi());
        values.put("avatar", bacsi.getImg());
        values.put("thongtin", bacsi.getThongtin());
        values.put("giakham", bacsi.getGiaKham());
        long result = db.insert("tbbacsi", null, values);
        db.close();
        return result != -1;
    }


    // lay danh sach bacsi theo chuyen khoa

    public Bacsi getBacsiById(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Bacsi bacsi = new Bacsi();
        try{
            Cursor cursor = db.query("tbbacsi", null, "id=?", new String[]{id}, null, null, null);
            if(cursor.moveToFirst()){
                bacsi.setId(cursor.getString(0));
                bacsi.setName(cursor.getString(1));
                bacsi.setChuyenkhoa(cursor.getString(2));
                bacsi.setDiachi(cursor.getString(3));

            }
            db.close();
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bacsi;
    }


    public ArrayList<Bacsi> getAllBacsi(ArrayList<Bacsi> bacSiList, String chuyenkhoa) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.query("tbbacsi", null, "chuyenkhoa=?", new String[]{chuyenkhoa}, null, null, null);
            while (cursor.moveToNext()) {
                Bacsi bacsi = new Bacsi(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                );
                bacSiList.add(bacsi);

            }
            db.close();
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return bacSiList;

    }

    // delete 1 bacsi
    public boolean deleteBacsi(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("tbbacsi", "id=?", new String[]{id});
        db.close();
        return result > 0;

    }


    public boolean addlichhen(lichhen lh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idbenhnhan", lh.getIdbenhnhan());
        values.put("idbacsi", lh.getIdbacsi());
        values.put("ngayhenkham", lh.getNgayhenkham());
        values.put("khumgiokham", lh.getKhunggiokham());
        values.put("trangthai", lh.getTrangthai());
        values.put("tenbenhnhan", lh.getNamebenhnhan());
        values.put("sodienthoai", lh.getSdtbenhnhan());
        values.put("diachi", lh.getDiachibenhnhan());
        values.put("avatarbs", lh.getAvatarbs());
        long result = db.insert("tblichhen", null, values);
        db.close();
        return result != -1;

    }

    public ArrayList<lichhen> getAlllichhen(String idbenhnhan){
        ArrayList<lichhen> lichhenList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.query("tblichhen", null, "idbenhnhan=?", new String[]{idbenhnhan}, null, null, null);
            while (cursor.moveToNext()) {
                lichhen lh = new lichhen(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9)
                );
                lichhenList.add(lh);
            }
            db.close();
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();

        }
        return lichhenList;
    }

    public boolean deletelichhen(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("tblichhen", "id=?", new String[]{id});
        db.close();
        return result > 0;
    }
    public void copyDatabaseFromAssets() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if (dbFile.exists()) {
            Log.d("DatabaseHelper", "Database đã tồn tại, không cần copy.");
            return;
        }

        try {
            InputStream myInput = context.getAssets().open(DATABASE_NAME);
            String outFileName = context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;

            File dbDir = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!dbDir.exists()) dbDir.mkdirs();

            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();

            Log.d("DatabaseHelper", "✅ Database copied successfully!");
        } catch (IOException e) {
            Log.e("DatabaseHelper", "❌ Copy database failed! " + e.getMessage());
        }
    }


}
