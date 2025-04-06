package com.example.bookingcare263;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.model.benhnhan;
import com.example.bookingcare263.model.chuyenkhoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHelper {

    // FIREBASE ACCOUTID

    // get all accout
    public static void getAllAccout(FirebaseCallBack<ArrayList<accout>> callBack){
        // dung ValueEventListenner

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList <accout> bacsiList = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    accout acc = data.getValue(accout.class);
                    bacsiList.add(acc);
                }
                    callBack.onSuccess(bacsiList);

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callBack.onFailed(error.getMessage());
            }
        });

    }

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
        ref.child(csyt.getId()).setValue(csyt).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callBack.onSuccess(null);
            } else {
                callBack.onFailed(task.getException().getMessage());
            }});
    }

    //
    public static void insertcosoyte(Cosoyte csyt, FirebaseCallBack callBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("tbsoyte");
        String id = ref.push().getKey();
        csyt.setId(id);
        ref.child(id).setValue(csyt).addOnCompleteListener(task -> {
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






}
