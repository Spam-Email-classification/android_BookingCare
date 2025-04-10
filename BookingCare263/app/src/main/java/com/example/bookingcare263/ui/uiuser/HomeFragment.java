package com.example.bookingcare263.ui.uiuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingcare263.Bacsi_details;
import com.example.bookingcare263.ChitietCSYT;
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.adapterus.adapterBacsi;
import com.example.bookingcare263.adapterus.adapterBaiviet;
import com.example.bookingcare263.adapterus.adapterItems;
import com.example.bookingcare263.adapterus.adaptercosoyte;
import com.example.bookingcare263.adapterus.adapterkhamchuyenkhoa;
import com.example.bookingcare263.databinding.FragmentHomeusBinding;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.model.Cosoyte;
import com.example.bookingcare263.model.Item;
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.model.chuyenkhoa;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeusBinding binding;
    private ArrayList<Item> listitems;

    private RecyclerView rcvItems, rcvbacsi, rcvchuyenkhoahome, rcvcosoyte;
    private adapterItems adapter;
    private TextView searchBar;

    private ViewFlipper viewFlipper;

    private adapterBaiviet adapterbv;
    private RecyclerView rcvbaiviet;
    private ArrayList<Baiviet> listbaiviet;



    adapterkhamchuyenkhoa adapterchuyenkh;
    private int [] imges = {R.drawable.flipper1, R.drawable.flipper2, R.drawable.flipper3, R.drawable.flipper4};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        anhxa();
        searchBar.setOnClickListener(v->{
           Intent intent = new Intent(getActivity(), Danhsachbacsi.class);
           intent.putExtra("title", "Tìm kiếm bác sĩ, chuyên khoa khám");
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
        // xly khung viet bai







        adapter.setOnItemClickListener(items -> {
            Item x = items;
            String itemName = items.getName();
            Intent intent;

            switch (itemName){
                case "KHÁM CHUYÊN KHOA":
                    intent = new Intent(getActivity(), listchuyenkhoa.class);
                    startActivity(intent);
                    break;



                case "ĐẶT LỊCH KHÁM":
                    intent = new Intent(getActivity(), Danhsachbacsi.class);
                    intent.putExtra("title", "ĐẶT LỊCH KHÁM");
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
        listitems.add(new Item("KHÁM CHUYÊN KHOA", R.drawable.main1, "Chuyên khoa cơ xương khớp"));
//        listitems.add(new Item("KHÁM TỔNG QUÁT", R.drawable.main3, " Chuyên khoa khám tổng quát"));

        listitems.add(new Item("ĐẶT LỊCH KHÁM", R.drawable.main4, "Chuyên khoa đặt lịch khám"));
//        listitems.add(new Item("XÉT NGHIỆM", R.drawable.main5, "Chuyên khoa xét nghiệm"));
        adapter =  new adapterItems(listitems);
        rcvItems.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rcvItems.setAdapter(adapter);

        // Xử lý recycleview bacsi

        ArrayList<Bacsi> listDoctors = new ArrayList<>();
        rcvbacsi = binding.rcvbacsi;


        ArrayList<accout> listaccout = new ArrayList<>();

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

        FirebaseHelper.getaccoutbyStatusAndRole( "Đang hoạt động", "bacsi", new FirebaseCallBack<ArrayList<accout>>()
                {
            @Override
            public void onSuccess(ArrayList<accout> data) {
                listaccout.clear();
                listaccout.addAll(data);

                // get listbac bysdt
                listDoctors.clear();
                for (accout accout : listaccout) {
                    FirebaseHelper.getBacsiBySdt(accout.getPhone(), new FirebaseCallBack<Bacsi>(){
                        @Override
                        public void onSuccess(Bacsi data) {
                            listDoctors.add(data);
                            adapterDoctors.notifyDataSetChanged();

                    }
                        @Override
                        public void onFailed(String message) {
                            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
            @Override
            public void onFailed(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });












        // hien thi chuyen khoa main

        rcvchuyenkhoahome = binding.rcvchuyenkhoahome;
        ArrayList<chuyenkhoa> listck = new ArrayList<>();
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

        FirebaseHelper.getChuyenKhoa(new FirebaseCallBack<ArrayList<chuyenkhoa>>() {
            @Override
            public void onSuccess(ArrayList<chuyenkhoa> data) {
                listck.clear();
                listck.addAll(data);
                adapterchuyenkh.notifyDataSetChanged();

            }

            @Override
            public void onFailed(String message) {

            }
        });





        // hien thi co so y te
        ArrayList<Cosoyte> cosoyteList = new ArrayList<>();
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

        FirebaseHelper.getcosoyte(new FirebaseCallBack<ArrayList<Cosoyte>>() {
            @Override
            public void onSuccess(ArrayList<Cosoyte> data) {
                cosoyteList.clear();
                cosoyteList.addAll(data);
                adaptercsyt.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String message) {

            }
        });

        // hien thi bai viet

        rcvbaiviet = binding.rcvbaiviet;
        listbaiviet = new ArrayList<>();
        adapterbv = new adapterBaiviet(listbaiviet);
        rcvbaiviet.setLayoutManager(new LinearLayoutManager(getContext()));
        rcvbaiviet.setAdapter(adapterbv);
        adapterbv.setOnItemClickListener( new adapterBaiviet.setItemClick() {
            @Override
            public void onItemClick(Baiviet baiviet) {

            }

            @Override
            public void onImageavatarClick(Baiviet baiviet) {

                FirebaseHelper.getBacsiBySdt(baiviet.getIduser(), new FirebaseCallBack<Bacsi>() {
                    @Override
                    public void onSuccess(Bacsi data) {
                        Intent intent;
                        intent = new Intent(getActivity(), Bacsi_details.class);
                        intent.putExtra("bacsi", data);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });



            }

            @Override
            public void onItemDatkhamClick(Baiviet baiviet) {
                // datlichkham


                FirebaseHelper.getBacsiBySdt(baiviet.getIduser(), new FirebaseCallBack<Bacsi>() {
                    @Override
                    public void onSuccess(Bacsi data) {
                        Intent intent = new Intent(getActivity(), Datlichkham.class);
                        intent.putExtra("bacsi", data);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailed(String message) {

                    }
                });



            }
            @Override
            public void onItemDeleteClick(Baiviet baiviet) {

            }

        });


        FirebaseHelper.getAllbaiviet(new FirebaseCallBack<ArrayList<Baiviet>>() {
            @Override
            public void onSuccess(ArrayList<Baiviet> data) {
                listbaiviet.clear();
                listbaiviet.addAll(data);
                adapterbv.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String message) {

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}