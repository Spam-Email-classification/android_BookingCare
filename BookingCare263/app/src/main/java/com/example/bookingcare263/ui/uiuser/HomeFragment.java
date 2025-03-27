package com.example.bookingcare263.ui.uiuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.Bacsi_details;
import com.example.bookingcare263.Danhsachbacsi;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.adapterus.adapterBacsi;
import com.example.bookingcare263.adapterus.adapterItems;
import com.example.bookingcare263.databinding.FragmentHomeusBinding;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Item;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeusBinding binding;
    private ArrayList<Item> listitems;

    private RecyclerView rcvItems;
    private RecyclerView rcvbacsi;
    private adapterItems adapter;
    private TextView searchBar;
    DatabaseHelper databaseHelper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        anhxa();
        searchBar.setOnClickListener(v->{
           Intent intent = new Intent(getActivity(), Danhsachbacsi.class);
           intent.putExtra("title", "BookingCare Search");
           intent.putExtra("thongtin", "Tự tin vươn tâm quốc tế");
           startActivity(intent);
        });


        return root;
    }

    private void anhxa() {
        searchBar = binding.searchBar;
        rcvItems = binding.rcvItems;

        listitems = new ArrayList<>();
        listitems.add(new Item("Cơ xương khớp", R.drawable.main1, "Chuyên khoa cơ xương khớp"));
        listitems.add(new Item("KHÁM NHA KHOA", R.drawable.main2, "Chuyên khoa khám nha khoa"));
        listitems.add(new Item("KHÁM TỔNG QUAT", R.drawable.main3, " Chuyên khoa khám tổng quát"));

        listitems.add(new Item("ĐẶT LỊCH KHÁM", R.drawable.main4, "Chuyên khoa đặt lịch khám"));
        listitems.add(new Item("XÉT NGHIỆM", R.drawable.main5, "Chuyên khoa xét nghiệm"));
        listitems.add(new Item("KHÁM TỪ XA", R.drawable.main6, "Chuyên khoa khám từ xa"));

        ArrayList<Bacsi> listDoctors = new ArrayList<>();
        rcvbacsi = binding.rcvbacsi;

        databaseHelper = new DatabaseHelper(getContext());
        listDoctors = databaseHelper.getAllBacsi(listDoctors);




        adapter =  new adapterItems(listitems);
        rcvItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcvItems.setAdapter(adapter);


        rcvbacsi = binding.rcvbacsi;
        adapterBacsi adapterDoctors = new adapterBacsi(listDoctors);
        rcvbacsi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvbacsi.setAdapter(adapterDoctors);

        adapterDoctors.setOnItemClickListener(bacsi->{

            String itemName = bacsi.getName();
            Intent intent;
            intent = new Intent(getActivity(), Bacsi_details.class);
            intent.putExtra("bacsi", bacsi);
            intent.putExtra("title", itemName);
            intent.putExtra("anh", bacsi.getImg());

            startActivity(intent);
        });



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