package com.example.bookingcare263.ui.uiuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.Bacsi_details;
import com.example.bookingcare263.ChitietCSYT;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.adapterus.adapterBacsi;
import com.example.bookingcare263.adapterus.adapterItems;
import com.example.bookingcare263.adapterus.adaptercosoyte;
import com.example.bookingcare263.adapterus.adapterkhamchuyenkhoa;
import com.example.bookingcare263.databinding.FragmentHomeusBinding;
import com.example.bookingcare263.listchuyenkhoa;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.Item;
import com.example.bookingcare263.model.chuyenkhoa;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeusBinding binding;
    private ArrayList<Item> listitems;

    private RecyclerView rcvItems, rcvbacsi, rcvchuyenkhoahome, rcvcosoyte;
    private adapterItems adapter;
    private TextView searchBar;
    DatabaseHelper databaseHelper;
    private ViewFlipper viewFlipper;



    adapterkhamchuyenkhoa adapterchuyenkh;
    private int [] imges = {R.drawable.flipper1, R.drawable.flipper2, R.drawable.flipper3, R.drawable.flipper4};

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

        for (int image : imges) {
            ImageView imageView = new ImageView(getContext()); // Tạo mới ImageView
            imageView.setImageResource(image);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }


        viewFlipper.setFlipInterval(3000); // 3 giây đổi ảnh
        viewFlipper.startFlipping();

        adapter.setOnItemClickListener(items -> {
            Item x = items;
            String itemName = items.getName();
            Intent intent;

            switch (itemName){
                case "Khám chuyên khoa":
                    intent = new Intent(getActivity(), listchuyenkhoa.class);
                    startActivity(intent);
                    break;

                case "KHÁM TỔNG QUÁT":
                    intent = new Intent(getActivity(), Danhsachbacsi.class);
                    intent.putExtra("title", itemName);
                    intent.putExtra("thongtin", x.getThongtin());
                    startActivity(intent);
                    break;


                case "ĐẶT LỊCH KHÁM":
                    intent = new Intent(getActivity(), Danhsachbacsi.class);
                    intent.putExtra("title", itemName);
                    intent.putExtra("thongtin", x.getThongtin());
                    startActivity(intent);
                    break;
                case "XÉT NGHIỆM":
                    intent = new Intent(getActivity(), Danhsachbacsi.class);
                    intent.putExtra("title", itemName);
                    intent.putExtra("thongtin", x.getThongtin());
                    startActivity(intent);
                    break;

            }
        });

        return root;
    }

    private void anhxa() {

        viewFlipper = binding.flipper;
        searchBar = binding.searchBar;
        rcvItems = binding.rcvItems;


        listitems = new ArrayList<>();
        listitems.add(new Item("Khám chuyên khoa", R.drawable.main1, "Chuyên khoa cơ xương khớp"));
        listitems.add(new Item("KHÁM TỔNG QUAT", R.drawable.main3, " Chuyên khoa khám tổng quát"));

        listitems.add(new Item("ĐẶT LỊCH KHÁM", R.drawable.main4, "Chuyên khoa đặt lịch khám"));
        listitems.add(new Item("XÉT NGHIỆM", R.drawable.main5, "Chuyên khoa xét nghiệm"));

        ArrayList<Bacsi> listDoctors = new ArrayList<>();
        rcvbacsi = binding.rcvbacsi;

        databaseHelper = new DatabaseHelper(getContext());
        listDoctors = databaseHelper.getAllBacsi(listDoctors);


        // danh sach kham main

        adapter =  new adapterItems(listitems);
        rcvItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcvItems.setAdapter(adapter);


        // hien thi bacsi main

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



        // hien thi chuyen khoa main

        rcvchuyenkhoahome = binding.rcvchuyenkhoahome;
        ArrayList<chuyenkhoa> listck = new ArrayList<>();
        listck = databaseHelper.getChuyenKhoa();
        adapterchuyenkh = new adapterkhamchuyenkhoa(listck, R.layout.bacsi_layout);
        rcvchuyenkhoahome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvchuyenkhoahome.setAdapter(adapterchuyenkh);

        adapterchuyenkh.setOnItemClickListener(chuyenkhoa -> {
            String itemName = chuyenkhoa.getTenchuyenkhoa();
            Intent intent;
            intent = new Intent(getActivity(), Danhsachbacsi.class);
            intent.putExtra("title", itemName);
            intent.putExtra("thongtin", chuyenkhoa.getThongtin());
            startActivity(intent);
                });





        // hien thi co so y te
        ArrayList<Cosoyte> cosoyteList = databaseHelper.getCosoyte();
        rcvcosoyte = binding.rcvcsyte;
        adaptercosoyte adaptercsyt = new adaptercosoyte(cosoyteList);
        rcvcosoyte.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvcosoyte.setAdapter(adaptercsyt);

        adaptercsyt.setOnItemClickListener(cosoyte -> {
            String itemName = cosoyte.getName();
            Intent intent;
            intent = new Intent(getActivity(), ChitietCSYT.class);
            intent.putExtra("cosoyte", cosoyte);

            startActivity(intent);

        });







    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}