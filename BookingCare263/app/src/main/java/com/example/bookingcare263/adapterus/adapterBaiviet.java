package com.example.bookingcare263.adapterus;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.DatabaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;

import java.util.ArrayList;

public class adapterBaiviet extends RecyclerView.Adapter<adapterBaiviet.ViewHolder> {
    ArrayList <Baiviet> listbaiviet;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baiviet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listbaiviet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgavatartbvshow, imganhbaivietshow;
        TextView txtnametbvshow, txtitleshowbv, txtcontentbv, txtxemthembv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgavatartbvshow = itemView.findViewById(R.id.imgavatartbvshow);
            imganhbaivietshow = itemView.findViewById(R.id.imganhbaivietshow);
            txtnametbvshow = itemView.findViewById(R.id.txtnametbvshow);
            txtitleshowbv = itemView.findViewById(R.id.txtitleshowbv);
            txtcontentbv = itemView.findViewById(R.id.txtcontentbv);
            txtxemthembv = itemView.findViewById(R.id.txtxemthembv);

        }
        public void bind(Baiviet baiviet){
            txtnametbvshow.setText(baiviet.getTitile());
            // getbac by iduser
            DatabaseHelper databaseHelper = new DatabaseHelper(itemView.getContext());
            Bacsi bacsi = databaseHelper.getBacsiBySdt(baiviet.getIduser());
            txtnametbvshow.setText(bacsi.getName());
            String avatarUri = bacsi.getImg();
            if (avatarUri != null && !avatarUri.isEmpty()) {
                Glide.with(imgavatartbvshow.getContext())
                        .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                        .circleCrop()
                        .into(imgavatartbvshow);
            } else {
                imgavatartbvshow.setImageResource(R.drawable.baseline_account_circle_24);
            }


            txtitleshowbv.setText(baiviet.getTitile());
            txtcontentbv.setText(baiviet.getContent());
            txtxemthembv.setText(baiviet.getTimestamp());

            String anhbaiviet = baiviet.getImg();
            if (avatarUri != null && !avatarUri.isEmpty()) {
                Glide.with(imganhbaivietshow.getContext())
                        .load(Uri.parse(anhbaiviet)) // Chuyển String thành Uri
                        .circleCrop()
                        .into(imganhbaivietshow);
            } else {
                imganhbaivietshow.setImageResource(R.drawable.baseline_account_circle_24);
            }
        }

    }
}
