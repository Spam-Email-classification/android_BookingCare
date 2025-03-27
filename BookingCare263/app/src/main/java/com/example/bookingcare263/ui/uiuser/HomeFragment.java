package com.example.bookingcare263.ui.uiuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.Danhsachbacsi;
import com.example.bookingcare263.R;
import com.example.bookingcare263.adapterus.adapterBacsi;
import com.example.bookingcare263.adapterus.adapterItems;
import com.example.bookingcare263.databinding.FragmentHomeBinding;
import com.example.bookingcare263.model.Item;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ArrayList<Item> listitems;

    private RecyclerView rcvItems;
    private RecyclerView rcvbacsi;
    private adapterItems adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        anhxa();


        return root;
    }

    private void anhxa() {
        rcvItems = binding.rcvItems;
        listitems = new ArrayList<>();
        listitems.add(new Item("Cơ xương khớp", R.drawable.main1));
        listitems.add(new Item("KHÁM NHA KHOA", R.drawable.main2));
        listitems.add(new Item("KHÁM TỔNG QUAT", R.drawable.main3));

        listitems.add(new Item("ĐẶT LỊCH KHÁM", R.drawable.main4));
        listitems.add(new Item("XÉT NGHIỆM", R.drawable.main5));
        listitems.add(new Item("KHÁM TỪ XA", R.drawable.main6));

        ArrayList<Item> listDoctors = new ArrayList<>();
        rcvbacsi = binding.rcvbacsi;

        listDoctors.add(new Item("BS. Nguyễn Văn A", R.drawable.bgdn));
        listDoctors.add(new Item("BS. Trần Thị B", R.drawable.bgdn));
        listDoctors.add(new Item("BS. Lê Minh C", R.drawable.bgdn));
        listDoctors.add(new Item("BS. Phạm Văn D", R.drawable.bgdn));


        adapter =  new adapterItems(listitems);
        rcvItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcvItems.setAdapter(adapter);


        rcvbacsi = binding.rcvbacsi;
        adapterBacsi adapterDoctors = new adapterBacsi(listDoctors);
        rcvbacsi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvbacsi.setAdapter(adapterDoctors);



        adapter.setOnItemClickListener(items -> {
            Item x = items;

            String itemName = items.getName();
            Intent intent;
            intent = new Intent(getActivity(), Danhsachbacsi.class);
            intent.putExtra("title", itemName);
            intent.putExtra("thongtin", x.getThongtin());
            startActivity(intent);

        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}