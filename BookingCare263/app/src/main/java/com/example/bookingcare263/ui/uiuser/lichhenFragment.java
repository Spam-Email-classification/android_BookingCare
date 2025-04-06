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
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.adapterus.adapterLichhen;
import com.example.bookingcare263.databinding.FragmentLichhenusBinding;
import com.example.bookingcare263.lichhenDetail;
import com.example.bookingcare263.model.lichhen;
import com.example.bookingcare263.UserActivity;
import java.util.ArrayList;

public class lichhenFragment extends Fragment {

    private RecyclerView rcvlichhen;
    private FragmentLichhenusBinding binding;
    private DatabaseHelper dbhelper;
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
        dbhelper = new DatabaseHelper(getContext());
        if (UserActivity.iduser != null) {
            if (UserActivity.roleuser.equals("user")) {
                listlichhen = dbhelper.getAlllichhenbyidbenhnhan(UserActivity.iduser);
            } else if (UserActivity.roleuser.equals("bacsi")) {
                listlichhen = dbhelper.getAlllichhenbyidbacsi(UserActivity.iduser);
            } else {
                listlichhen = dbhelper.getAlllichhenbyidbacsi(UserActivity.iduser);
            }

            rcvlichhen = binding.rcvlichhen;
            adapter = new adapterLichhen(listlichhen);
            rcvlichhen.setLayoutManager(new LinearLayoutManager(getContext()));
            rcvlichhen.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            // Chỉ gọi setOnItemClickListener một lần
            adapter.setOnItemClickListener(new adapterLichhen.OnItemClickListener() {
                @Override
                public void onItemClick(lichhen lichhen) {
                    Intent intent = new Intent(getContext(), lichhenDetail.class);
                    intent.putExtra("lichhen", lichhen);
                    intent.putExtra("avatarbs", lichhen.getAvatarbs());
                    startActivity(intent);
                }
            });
        }
    }

    public void loadData() {
        dbhelper = new DatabaseHelper(getContext());
        listlichhen = dbhelper.getAlllichhenbyidbenhnhan(UserActivity.iduser);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
