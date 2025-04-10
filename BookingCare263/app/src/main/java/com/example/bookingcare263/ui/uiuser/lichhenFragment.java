package com.example.bookingcare263.ui.uiuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.adapterus.adapterLichhen;
import com.example.bookingcare263.databinding.FragmentLichhenusBinding;
import com.example.bookingcare263.lichhenDetail;
import com.example.bookingcare263.model.lichhen;
import com.example.bookingcare263.UserActivity;
import java.util.ArrayList;

public class lichhenFragment extends Fragment {

    private RecyclerView rcvlichhen;
    private FragmentLichhenusBinding binding;
    private ArrayList<lichhen> listlichhen;
    private adapterLichhen adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLichhenusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        anhxa();
        return root;
    }

    private void anhxa() {

        rcvlichhen = binding.rcvlichhen;
        listlichhen = new ArrayList<>();
        adapter = new adapterLichhen(listlichhen);
        rcvlichhen.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvlichhen.setAdapter(adapter);
        adapter.setOnItemClickListener(new adapterLichhen.OnItemClickListener() {
            @Override
            public void onItemClick(lichhen lichhen) {
                Intent intent = new Intent(getContext(), lichhenDetail.class);
                intent.putExtra("lichhen", lichhen);
                startActivity(intent);
            }
        });

        if (UserActivity.iduser != null) {
            if (UserActivity.roleuser.equals("user")) {

                FirebaseHelper.getlichhenbyidbenhnhan(UserActivity.iduser, new FirebaseCallBack<ArrayList<lichhen>>() {
                    @Override
                    public void onSuccess(ArrayList<lichhen> data) {
                        listlichhen.clear();
                        listlichhen.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailed(String message) {

                    }
                });



            } else {
                FirebaseHelper.getlichhenbyidbacsi(UserActivity.iduser, new FirebaseCallBack<ArrayList<lichhen>>() {
                    @Override
                    public void onSuccess(ArrayList<lichhen> data) {
                        listlichhen.clear();
                        listlichhen.addAll(data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });
            }



        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
