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
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Cosoyte;


import java.util.ArrayList;

public class adaptercosoyte  extends RecyclerView.Adapter<adaptercosoyte.ViewHolder> {
    ArrayList <Cosoyte> listitems;

    public OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Cosoyte items);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public adaptercosoyte(ArrayList<Cosoyte> listitems) {
        this.listitems = listitems;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.csyte_layout,parent,false);
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
        public void bind(Cosoyte items){
            txtitems.setText(items.getName());

            String avatarUri = items.getImg();
            if (avatarUri != null && !avatarUri.isEmpty()) {
                Glide.with(imgIcon.getContext())
                        .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                        .error(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu load thất bại
                        .into(imgIcon);
            } else {
                imgIcon.setImageResource(R.drawable.baseline_account_circle_24);
            }
        }
    }
}
