package com.example.bookingcare263.adapterus;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;


import java.io.File;
import java.util.ArrayList;

public class adapterbsAd extends RecyclerView.Adapter<adapterbsAd.ViewHolder> {
    ArrayList <Bacsi> listbacsi;
    private onClickListener listener;
    public void setOnClickListener(onClickListener listener) {
        this.listener = listener;
    }
    public interface onClickListener {
        void onItemClick(Bacsi bacsi);
        void onDeleteClick(Bacsi bacsi);
        void onFixClick(Bacsi bacsi);
    }
    public adapterbsAd(ArrayList<Bacsi> listbacsi) {
        this.listbacsi = listbacsi;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bsad,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listbacsi.get(position));
        holder.itemView.setOnClickListener(v->{
            if (listener != null) {
                listener.onItemClick(listbacsi.get(position));
            }
        });
        holder.imgfix.setOnClickListener(v->{
            if (listener != null) {
                listener.onFixClick(listbacsi.get(position));
            }
        });
        holder.imgdelete.setOnClickListener(v->{
            if (listener != null) {
                listener.onDeleteClick(listbacsi.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listbacsi.size();
    }

    public void updateList(ArrayList<Bacsi> newList) {
        listbacsi.clear();
        listbacsi.addAll(newList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgava, imgfix, imgdelete;
        private TextView txttenbs;
        private TextView txtchuyenkhoa;
        private TextView txtdiachikham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgava = itemView.findViewById(R.id.imgavaad);
            txttenbs = itemView.findViewById(R.id.txttenbsad);
            txtchuyenkhoa = itemView.findViewById(R.id.txtchuyenkhoaad);
            txtdiachikham = itemView.findViewById(R.id.txtdiachikhamad);
            imgfix = itemView.findViewById(R.id.imgfix);
            imgdelete = itemView.findViewById(R.id.imgdelete);

        }
        public void bind(Bacsi bacsi) {

            String avatarUri = bacsi.getImg();
            if (avatarUri != null && !avatarUri.isEmpty()) {
                Glide.with(imgava.getContext())
                        .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                        .circleCrop()
                        .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                        .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                        .into(imgava);
            } else {
                imgava.setImageResource(R.drawable.baseline_account_circle_24);
            }

            txttenbs.setText(bacsi.getName());
            txtchuyenkhoa.setText(bacsi.getChuyenkhoa());
            txtdiachikham.setText(bacsi.getDiachi());


        }

    }
}

