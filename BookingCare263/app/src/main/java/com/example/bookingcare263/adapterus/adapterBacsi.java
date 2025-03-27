package com.example.bookingcare263.adapterus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Item;

import java.util.ArrayList;

public class adapterBacsi  extends RecyclerView.Adapter<adapterBacsi.ViewHolder> {
    ArrayList <Bacsi> listitems;

    private OnBacsiClickListener listener;
    public interface OnBacsiClickListener {
        void onItemClick(Bacsi items);
    }


    public void setOnItemClickListener(OnBacsiClickListener listener) {
        this.listener = listener;
    }
    public adapterBacsi(ArrayList<Bacsi> listitems) {
        this.listitems = listitems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bacsi_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(listitems.get(position));
        holder.itemView.setOnClickListener(v -> {
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
        public void bind(Bacsi items){
            txtitems.setText(items.getName());
            Glide.with(imgIcon.getContext())
                    .load(items.getImg())  // Nếu `items.getIcon()` trả về ID ảnh drawable
                    .apply(RequestOptions.circleCropTransform()) // Làm tròn ảnh
                    .into(imgIcon);
            imgIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}
