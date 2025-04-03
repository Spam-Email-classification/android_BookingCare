package com.example.bookingcare263;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.benhnhan;
import com.example.bookingcare263.model.chuyenkhoa;
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
    // get benhnhan by sodt
    public benhnhan getbenhnhan(String sodt){
        SQLiteDatabase db = this.getReadableDatabase();
        benhnhan benhnhan = new benhnhan();
        try{
            Cursor cursor = db.query("tbbenhnhan", null, "sodienthoai=?", new String[]{sodt}, null, null, null);
            if(cursor.moveToFirst()){
                benhnhan.setId(cursor.getString(0));

        }        db.close();
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return benhnhan;
    }

    public boolean insertbenhnhan(benhnhan bn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", bn.getTen());
        values.put("sodienthoai", bn.getSodienthoai());
        values.put("diachi", bn.getDiachi());
        values.put("gioitinh", bn.getGioitinh());
        values.put("ngaysinh", bn.getNgaysinh());
        values.put("benhlynen", bn.getBenhlynen());
        long result = db.insert("tbbenhnhan", null, values);
        db.close();
        return result != -1;
    }

    // update benh nhan

    public boolean updatebenhnha(benhnhan bn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", bn.getTen());
        values.put("sodienthoai", bn.getSodienthoai());
        values.put("diachi", bn.getDiachi());
        values.put("gioitinh", bn.getGioitinh());
        values.put("ngaysinh", bn.getNgaysinh());
        values.put("benhlynen", bn.getBenhlynen());
        int result = db.update("tbbenhnhan", values, "id=?", new String[]{bn.getId()});
        db.close();
        return result > 0;

    }



    //Chuyen khoa

    public ArrayList <chuyenkhoa> getChuyenKhoa(){
        ArrayList<chuyenkhoa> chuyenkhoaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor cursor = db.query("tbchuyenkhoa", null, null, null, null, null, null);
            while (cursor.moveToNext()){
                chuyenkhoa chuyenkhoa = new chuyenkhoa(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                chuyenkhoaList.add(chuyenkhoa);
            }
            db.close();
            cursor.close();
        }catch (Exception e){
            }

        return chuyenkhoaList;

    }
    public boolean insertChuyenKhoa(chuyenkhoa ck){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenchuyenkhoa", ck.getTenchuyenkhoa());
        values.put("img", ck.getImg());
        values.put("thongtin", ck.getThongtin());
        long result = db.insert("tbchuyenkhoa", null, values);
        db.close();
        return result != -1;
    }

    public boolean deleteChuyenKhoa(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("tbchuyenkhoa", "id=?", new String[]{id});
        db.close();
        return result > 0;
    }
    public boolean updateChuyenKhoa(chuyenkhoa ck){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenchuyenkhoa", ck.getTenchuyenkhoa());
        values.put("img", ck.getImg());
        values.put("thongtin", ck.getThongtin());
        int result = db.update("tbchuyenkhoa", values, "id=?", new String[]{ck.getId()});
        db.close();
        return result > 0;
    }


    // get cosoyte
    public ArrayList<Cosoyte> getCosoyte(){
        ArrayList<Cosoyte> cosoyteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.query("tbsoyte", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Cosoyte cosoyte = new Cosoyte(
                        cursor.getInt(0),
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
                cosoyteList.add(cosoyte);
            }
            db.close();
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return cosoyteList;
    }



    public boolean insertCosoyte(Cosoyte cosoyte) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", cosoyte.getName());
        values.put("img", cosoyte.getImg());
        values.put("diachi", cosoyte.getDiachi());
        values.put("thongtin", cosoyte.getThongtin());
        values.put("masogiayphep", cosoyte.getMasogiayphep());
        values.put("website", cosoyte.getWebsite());
        values.put("sodienthoai", cosoyte.getSdt());
        values.put("email", cosoyte.getEmail());
        values.put("chuyenkhoa", cosoyte.getChuyenkhoa());

        long result = db.insert("tbsoyte", null, values);
        db.close();
        return result != -1;

    }

    public boolean deletecsyt(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("tbsoyte", "id=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
    public boolean updatecsyt(Cosoyte cosoyte){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", cosoyte.getName());
        values.put("img", cosoyte.getImg());
        values.put("diachi", cosoyte.getDiachi());
        values.put("thongtin", cosoyte.getThongtin());
        values.put("masogiayphep", cosoyte.getMasogiayphep());
        values.put("website", cosoyte.getWebsite());
        values.put("sodienthoai", cosoyte.getSdt());
        values.put("email", cosoyte.getEmail());
        values.put("chuyenkhoa", cosoyte.getChuyenkhoa());
        int result = db.update("tbsoyte", values, "id=?", new String[]{String.valueOf(cosoyte.getId())});
        db.close();
        return result > 0;

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
        values.put("sogiayphephanhnghe", bacsi.getSogiayphephanhnghe());
        values.put("email", bacsi.getEmail());
        values.put("sdt", bacsi.getSdt());
        long result = db.insert("tbbacsi", null, values);
        db.close();
        return result != -1;
    }
    // update bacsi
    public boolean updateBacsi(Bacsi bacsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", bacsi.getName());
        values.put("chuyenkhoa", bacsi.getChuyenkhoa());
        values.put("diachi", bacsi.getDiachi());
        values.put("avatar", bacsi.getImg());
        values.put("thongtin", bacsi.getThongtin());
        values.put("giakham", bacsi.getGiaKham());
        values.put("sogiayphephanhnghe", bacsi.getSogiayphephanhnghe());
        values.put("email", bacsi.getEmail());
        values.put("sdt", bacsi.getSdt());
        int result = db.update("tbbacsi", values, "id=?", new String[]{bacsi.getId()});
        db.close();
        return result > 0;
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
                bacsi.setImg(cursor.getString(4));
                bacsi.setThongtin(cursor.getString(5));
                bacsi.setGiaKham(cursor.getString(6));
                bacsi.setSogiayphephanhnghe(cursor.getString(7));
                bacsi.setEmail(cursor.getString(8));
                bacsi.setSdt(cursor.getString(9));

            }
            db.close();
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bacsi;
    }
    public ArrayList<Bacsi> getAllBacsi(ArrayList<Bacsi> bacSiList) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor cursor = db.query("tbbacsi", null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Bacsi bacsi = new Bacsi(
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
                bacSiList.add(bacsi);

            }
            db.close();
            cursor.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return bacSiList;

    }



    public ArrayList<Bacsi> getBacsiByChuyenKhoa(ArrayList<Bacsi> bacSiList, String chuyenkhoa) {
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
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9)
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
        values.put("tenbs", lh.getNamebs());
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
                        cursor.getString(9),
                        cursor.getString(10)

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
