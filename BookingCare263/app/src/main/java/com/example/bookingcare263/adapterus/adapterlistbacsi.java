package com.example.bookingcare263.adapterus;

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

public class adapterlistbacsi extends RecyclerView.Adapter<adapterlistbacsi.ViewHolder> {
    ArrayList <Bacsi> listbacsi;
    private onClickListener listener;
    public void setOnClickListener(onClickListener listener) {
        this.listener = listener;
    }
    public interface onClickListener {
        void onItemClick(Bacsi bacsi);
    }
    public adapterlistbacsi(ArrayList<Bacsi> listbacsi) {
        this.listbacsi = listbacsi;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_bacsi,parent,false);
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
    }

    @Override
    public int getItemCount() {
        return listbacsi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgava;
        private TextView txttenbs;
        private TextView txtchuyenkhoa;
        private TextView txtdiachikham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgava = itemView.findViewById(R.id.imgava);
            txttenbs = itemView.findViewById(R.id.txttenbs);
            txtchuyenkhoa = itemView.findViewById(R.id.txtchuyenkhoa);
            txtdiachikham = itemView.findViewById(R.id.txtdiachikham);

        }
        public void bind(Bacsi bacsi) {
            String imagePath = bacsi.getImg();
            if(imagePath != null){
                Toast.makeText(imgava.getContext(), imagePath, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(imgava.getContext(), "Không có ảnh", Toast.LENGTH_SHORT).show();
            }
            Glide.with(imgava.getContext())
                    .load(new File(imgava.getContext().getFilesDir(), imagePath) )
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.baseline_account_circle_24)
                    .into(imgava);



            txttenbs.setText(bacsi.getName());
            txtchuyenkhoa.setText(bacsi.getChuyenkhoa());
            txtdiachikham.setText(bacsi.getDiachi());


        }

    }
}

