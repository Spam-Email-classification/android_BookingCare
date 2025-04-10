package com.example.bookingcare263;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.model.Chuyenkhoacsyt;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.model.benhnhan;
import com.example.bookingcare263.model.chuyenkhoa;
import com.example.bookingcare263.model.lichhen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;



public class FirebaseHelper {

    // FIREBASE ACCOUTID

    // get all accout

    // ham gui thong bao


        private static final String FCM_URL = "https://fcm.googleapis.com/v1/projects/YOUR_PROJECT_ID/messages:send";








    // get accout by status and role

    public static void getaccoutbyStatusAndRole(String status, String role, FirebaseCallBack<ArrayList<accout>> callback){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<accout> listaccout = new ArrayList<>();
                        for(DataSnapshot data: snapshot.getChildren()){
                            accout acc = data.getValue(accout.class);
                            if(acc.getStatus().equals(status) && acc.getAs().equals(role))
                                listaccout.add(acc);
                        }
                        Collections.reverse(listaccout);
                        callback.onSuccess(listaccout);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    // get accout by role
    public static void getaccoutbyrole(String role, FirebaseCallBack<ArrayList<accout>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.orderByChild("as").equalTo(role)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<accout> listaccout = new ArrayList<>();
                        for(DataSnapshot data: snapshot.getChildren()){
                            accout acc = data.getValue(accout.class);
                            listaccout.add(acc);
                        }
                        Collections.reverse(listaccout);
                        callBack.onSuccess(listaccout);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callBack.onFailed(error.getMessage());
                    }
                });
    }

    // add accout
    public static void addAccout(accout accout, FirebaseCallBack<accout> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(accout.getPhone()).setValue(accout).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(accout);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });
    }

    // get accout by id
    public static void getaccbyid(String id, FirebaseCallBack<accout> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                accout acc = snapshot.getValue(accout.class);
                callBack.onSuccess(acc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public static void updateAccout(Bacsi bs, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(bs.getSdt()).setValue(bs).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });
    }

    // getAccout danghoatdong
    public static void getaccoutbyStatusAndRoletinh(String status, String role, FirebaseCallBack<ArrayList<accout>> callback){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<accout> listaccout = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    accout acc = data.getValue(accout.class);
                    if(acc.getStatus().equals(status) && acc.getAs().equals(role))
                        listaccout.add(acc);
                }
                Collections.reverse(listaccout);
                callback.onSuccess(listaccout);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }






    // addlichhen
    public static void addlichhen(lichhen lh, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_lichhen");
        String id = ref.push().getKey();
        lh.setId(id);
        ref.child(id).setValue(lh).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });
    }

    // get lichhen by idbenhnhan
    public static void getlichhenbyidbenhnhan(String idbenhnhan, FirebaseCallBack<ArrayList<lichhen>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_lichhen");
        ref.orderByChild("idbenhnhan").equalTo(idbenhnhan)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList <lichhen> listlh = new ArrayList<>();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            lichhen lh = data.getValue(lichhen.class);
                            listlh.add(lh);
                        }
                        Collections.reverse(listlh);
                        callBack.onSuccess(listlh);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callBack.onFailed(error.getMessage());

                    }
                });
    }

    // get lichhen by idbacsi
    public static void getlichhenbyidbacsi(String idbacsi, FirebaseCallBack<ArrayList<lichhen>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_lichhen");
        ref.orderByChild("idbacsi").equalTo(idbacsi)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<lichhen> listlh = new ArrayList<>();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            lichhen lh = data.getValue(lichhen.class);
                            listlh.add(lh);
                        }
                        Collections.reverse(listlh);
                        callBack.onSuccess(listlh);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    // update trang thai lich hen
    public static void updateTrangThai(String id, String trangthai){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_lichhen");
        ref.child(id).child("trangthai").setValue(trangthai);
    }


    // xoa lich hen{
    public static void deletelichhen(String id){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_lichhen");
        ref.child(id).removeValue();
    }










    // add bennhan tb_benhnhan
    public static void addbenhnhan(benhnhan bn, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_benhnhan");
        ref.child(bn.getSodienthoai()).setValue(bn).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });
    }
    // get benh nhan by sdt
    public static void getbenhnhanBySdt(String sdt, FirebaseCallBack<benhnhan> callBack) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_benhnhan");
        ref.orderByChild("sodienthoai").equalTo(sdt)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        benhnhan bn = new benhnhan();
                        for (DataSnapshot data : snapshot.getChildren()) {
                             bn = data.getValue(benhnhan.class);
                        }
                        callBack.onSuccess(bn);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callBack.onFailed(error.getMessage());
                    }
                });


    }

    // delete benhnhan by sdt
    public static void deletebenhnha(String sdt){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference( "tb_benhnhan");
        ref.child(sdt).removeValue();
    }

    // update benh nhan
    public static void updatebenhnha(benhnhan bn, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_benhnhan");
        ref.child(bn.getSodienthoai()).setValue(bn).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }});
    }




    // add bacsi tb_bacsi
    public static void addBacsi(Bacsi bs, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
        ref.child(bs.getSdt()).setValue(bs).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });

    }

    // get bacsi by sdt
    public static void getBacsiBySdt(String sdt, FirebaseCallBack<Bacsi> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
        ref.orderByChild("sdt").equalTo(sdt)
                .addListenerForSingleValueEvent (new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Bacsi bs = new Bacsi();
                        for(DataSnapshot data: snapshot.getChildren()){
                            bs = data.getValue(Bacsi.class);
                        }
                        callBack.onSuccess(bs);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public static void getttBacsiBySdt(String sdt, FirebaseCallBack<Bacsi> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
        ref.orderByChild("sdt").equalTo(sdt)
                .addValueEventListener (new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Bacsi bs = new Bacsi();
                        for(DataSnapshot data: snapshot.getChildren()){
                            bs = data.getValue(Bacsi.class);
                        }
                        callBack.onSuccess(bs);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }




    // update bacsi bysdt
    public static void updateBacsi(Bacsi bs, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
        ref.child(bs.getSdt()).setValue(bs).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });
    }

    // xoa bac si
    public static void deleteBacsi(String sdt){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
        ref.child(sdt).removeValue();
    }

    // get bacsi by chuyen kkhoa
    public static void getBacsiByChuyenkhoa(String chuyenkhoa, FirebaseCallBack<ArrayList<Bacsi>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
        ref.orderByChild("chuyenkhoa").equalTo(chuyenkhoa)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Bacsi> listbs = new ArrayList<>();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Bacsi bs = data.getValue(Bacsi.class);
                            listbs.add(bs);
                        }
                        callBack.onSuccess(listbs);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callBack.onFailed(error.getMessage());

                    }
                });
    }


    // get bacsi by chuyen khoa va sdt
    public static void getBacsiByChuyenkhoaAndSdt(String chuyenkhoa, String sdt, FirebaseCallBack<Bacsi> callBack) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
        ref.orderByChild("sdt").equalTo(sdt)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Bacsi bs = new Bacsi();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            bs = data.getValue(Bacsi.class);
                        }
                        if(bs.getChuyenkhoa().equals(chuyenkhoa))
                            callBack.onSuccess(bs);
                        else callBack.onFailed("Không tìm thấy bác sĩ phù hợp");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    public static void getBacsiBySdtfilter( String sdt, FirebaseCallBack<Bacsi> callBack) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_bacsi");
        ref.orderByChild("sdt").equalTo(sdt)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Bacsi bs = new Bacsi();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            bs = data.getValue(Bacsi.class);
                        }
                            callBack.onSuccess(bs);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callBack.onFailed(error.getMessage());

                    }
                });
    }



    // add baiviet
     public static void addbaiviet(Baiviet bv, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_baiviet");
        String id = ref.push().getKey();
        bv.setId(id);
        ref.child(id).setValue(bv).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });
     }

    public  static void getbaivietbyiduser(String iduser, FirebaseCallBack<ArrayList<Baiviet>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference( "tb_baiviet");
        ref.orderByChild("iduser").equalTo(iduser)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList <Baiviet> listbv = new ArrayList<>();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Baiviet bv = data.getValue(Baiviet.class);
                            listbv.add(bv);
                        }
                        callBack.onSuccess(listbv);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callBack.onFailed(error.getMessage());

                    }
                });
    }

    public static void getAllbaiviet(FirebaseCallBack<ArrayList<Baiviet>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_baiviet");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <Baiviet> listbv = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Baiviet bv = data.getValue(Baiviet.class);
                    listbv.add(bv);
                }
                Collections.reverse(listbv);
                callBack.onSuccess(listbv);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callBack.onFailed(error.getMessage());

            }
        });
    }

    public static void deletebaiviet(String id){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_baiviet");
        ref.child(id).removeValue();
    }









                                   // get chuyen khoa
    public static void getChuyenKhoa(FirebaseCallBack<ArrayList<chuyenkhoa>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_chuyenkhoa");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <chuyenkhoa> listck = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    chuyenkhoa ck = data.getValue(chuyenkhoa.class);
                    listck.add(ck);
                }
                callBack.onSuccess(listck);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callBack.onFailed(error.getMessage());

            }
        });
    }

    // add chuyenkhoa

    public static void addchuyenkhoa(chuyenkhoa ck , FirebaseCallBack callBack) {


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_chuyenkhoa");
        String id = ref.push().getKey();
        ck.setId(id);

        ref.child(id).setValue(ck).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });
    }

    public static void updatechuyenkhoa(chuyenkhoa ck, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_chuyenkhoa");
        ref.child(ck.getId()).setValue(ck).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }});
    }

    public static void deletechuyenkhoa(String id){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_chuyenkhoa");
        ref.child(id).removeValue();

    }

    // get cosyte

    public static void  getcosoyte(FirebaseCallBack<ArrayList<Cosoyte>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tbsoyte");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <Cosoyte> listck = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Cosoyte ck = data.getValue(Cosoyte.class);
                    listck.add(ck);
                }
                callBack.onSuccess(listck);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callBack.onFailed(error.getMessage());

            }
        });
    }

    // update csyte
    public static void updatcosoyte(Cosoyte csyt, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tbsoyte");
        ref.child(csyt.getSdt()).setValue(csyt).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }});
    }

    //
    public static void insertcosoyte(Cosoyte csyt, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tbsoyte");

        ref.child(csyt.getSdt()).setValue(csyt).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }});

    }


    // delete cosyt
    public static void deletecsyt(String id){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tbsoyte");
        ref.child(id).removeValue();
    }

    // get csyt by id
    public static void getcsytbyid(String id, FirebaseCallBack<Cosoyte> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tbsoyte");
        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cosoyte csyt = snapshot.getValue(Cosoyte.class);
                callBack.onSuccess(csyt);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    // get csyt getcsytbyid
    public static void getcsytbysdt(String sdt, FirebaseCallBack<Cosoyte> callBack){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tbsoyte");
        ref.orderByChild("sdt").equalTo(sdt)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cosoyte csyt = snapshot.getValue(Cosoyte.class);
                callBack.onSuccess(csyt);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    // add chuyenkoa csyt

    public static void addchuyenkhoaCSYT(Chuyenkhoacsyt ck, FirebaseCallBack callBack) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_chuyenkhoaCSYT");
        String id = ref.push().getKey();
        ck.setId(id);
        ref.child(id).setValue(ck).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }
        });
    }

            // get ckcsyt by sdtcsyt

    public static void getchuyenkhoaCSYTbyid(String sdtcsyt, FirebaseCallBack<ArrayList<Chuyenkhoacsyt>> callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_chuyenkhoaCSYT");
        ref.orderByChild("sdtcsyt").equalTo(sdtcsyt)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList <Chuyenkhoacsyt> listck = new ArrayList<>();
                        for (DataSnapshot data : snapshot.getChildren()) {
                            Chuyenkhoacsyt ck = data.getValue(Chuyenkhoacsyt.class);
                            listck.add(ck);
                        }
                        callBack.onSuccess(listck);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callBack.onFailed(error.getMessage());

                    }
                });
    }



// delet chuyenkhoa csyt
    public static void deletechuyenkhoaCSYT(String id){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tb_chuyenkhoaCSYT");
        ref.child(id).removeValue();
    }





}
