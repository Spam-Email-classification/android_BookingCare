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
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Item;
import com.example.bookingcare263.model.chuyenkhoa;

import java.util.ArrayList;

public class adapterkhamchuyenkhoa  extends RecyclerView.Adapter<adapterkhamchuyenkhoa.ViewHolder> {
    ArrayList <chuyenkhoa> listitems;
    int layout;
    public OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(chuyenkhoa items);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public adapterkhamchuyenkhoa(ArrayList<chuyenkhoa> listitems, int layout) {
        this.listitems = listitems;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(listitems.get(position));
        holder.itemView.setOnClickListener(v->{
            if (listener != null) {
                listener.onItemClick(listitems.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtitems;
        ImageView imgIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtitems = itemView.findViewById(R.id.txtitemsbs);
            imgIcon = itemView.findViewById(R.id.imgiconbs);
        }
        public void bind(chuyenkhoa items){
            txtitems.setText(items.getTenchuyenkhoa());

            if(items.getImg() != null)
            Glide.with(imgIcon.getContext())
                    .load(Uri.parse(items.getImg())) // Chuyển String thành Uri
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                    .into(imgIcon);

            else{
                imgIcon.setImageResource(R.drawable.baseline_account_circle_24);
            }


        }
    }
}
