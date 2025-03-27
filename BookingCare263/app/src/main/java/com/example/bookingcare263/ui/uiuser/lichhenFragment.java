package com.example.bookingcare263.ui.uiuser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.UserActivity;
import com.example.bookingcare263.adapterus.adapterLichhen;
import com.example.bookingcare263.databinding.FragmentLichhenusBinding;
import com.example.bookingcare263.model.lichhen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class lichhenFragment extends Fragment {
    RecyclerView rcvlichhen;

    private FragmentLichhenusBinding binding;
    DatabaseHelper dbhelper;
    ArrayList <lichhen> listlichhen;
    adapterLichhen adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLichhenusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        anhxa();

        return root;
    }

    private void anhxa() {
        dbhelper = new DatabaseHelper(getContext());
        listlichhen = dbhelper.getAlllichhen(UserActivity.iduser);

        Log.d("listlichhen size", listlichhen.size() + "");

        rcvlichhen = binding.rcvlichhen;
        adapter = new adapterLichhen(listlichhen);
        rcvlichhen.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvlichhen.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
    // loaddu lieu
    public void loadData(){
        dbhelper = new DatabaseHelper(getContext());
        listlichhen = dbhelper.getAlllichhen(UserActivity.iduser);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}