package com.example.bookingcare263.adapterus;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingcare263.R;
import com.example.bookingcare263.UserActivity;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Chuyenkhoacsyt;

import java.util.ArrayList;

public class adapterckcsyt extends RecyclerView.Adapter<adapterckcsyt.ViewHolder> {
    ArrayList<Chuyenkhoacsyt> listckcsyte;
    private OnClickListener listener;
    public interface OnClickListener {
        void onItemClick(Chuyenkhoacsyt items);
        void onItemdatkham(Chuyenkhoacsyt items);
        void onItemDelete(Chuyenkhoacsyt items);
    }
    public void setOnItemClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public adapterckcsyt(ArrayList<Chuyenkhoacsyt> listckcsyte) {
        this.listckcsyte = listckcsyte;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ckcsyt,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listckcsyte.get(position));

        holder.btndatkham.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemdatkham(listckcsyte.get(position));
            }
        });
        holder.imgdeleteckkham.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemDelete(listckcsyte.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listckcsyte.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttenkhoacsyt, txtdckhoacsyt;
        ImageView avatarkhoacsyt, imgdeleteckkham;
        Button btndatkham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenkhoacsyt = itemView.findViewById(R.id.txttenkhoacsyt);
            txtdckhoacsyt = itemView.findViewById(R.id.txtdckhoacsyt);
            avatarkhoacsyt = itemView.findViewById(R.id.imgavatarkhoacsyt);
            btndatkham = itemView.findViewById(R.id.btnckdatkham);
            imgdeleteckkham = itemView.findViewById(R.id.imgdeleteckkham);


        }

        public void  bind(Chuyenkhoacsyt ckcsyt){
            if(UserActivity.iduser == null || !UserActivity.iduser.equals(ckcsyt.getSdtcsyt())){
                imgdeleteckkham.setVisibility(View.GONE);
            } else {
                imgdeleteckkham.setVisibility(View.VISIBLE);
            }
            if(UserActivity.iduser != null && UserActivity.iduser.equals(ckcsyt.getSdtcsyt())){
                btndatkham.setVisibility(View.GONE);
            }
            txttenkhoacsyt.setText(ckcsyt.getTenkhoa());
            txtdckhoacsyt.setText(ckcsyt.getDiachi());
            String avatarUri = ckcsyt.getImg();
            if (avatarUri != null && !avatarUri.isEmpty()) {
                Glide.with(avatarkhoacsyt.getContext())
                        .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                        .into(avatarkhoacsyt);
            } else {
                avatarkhoacsyt.setImageResource(R.drawable.baseline_account_circle_24);
            }

        }
    }
}
