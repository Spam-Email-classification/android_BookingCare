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
import com.example.bookingcare263.model.lichhen;

import java.io.File;
import java.util.ArrayList;

public class adapterLichhen extends RecyclerView.Adapter<adapterLichhen.ViewHolder>{
    ArrayList<lichhen> listlichhen;

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(lichhen lichhen);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }




    public adapterLichhen(ArrayList<lichhen> listlichhen) {
        this.listlichhen = listlichhen;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichhen,parent,false);
        return new adapterLichhen.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        lichhen lichhen = listlichhen.get(position);
        holder.bind(lichhen);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(lichhen);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listlichhen.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{

        TextView txttrangthai, txtnamebacsi, txtgiokham, txtnambenhnhan;
        ImageView imglichhen;


        public ViewHolder(View itemView) {
            super(itemView);
            txttrangthai = itemView.findViewById(R.id.txttrangthai);
            txtnamebacsi = itemView.findViewById(R.id.txtnamebacsi);
            txtgiokham = itemView.findViewById(R.id.txtgiokham);
            txtnambenhnhan = itemView.findViewById(R.id.txtnamebenhnhan);
            imglichhen = itemView.findViewById(R.id.imglichhen);


        }
        public void bind(lichhen lichhen){
            
            txttrangthai.setText(lichhen.getTrangthai());

            // lay namebas
//            String namebs = lichhen.getNamebs();

            txtnamebacsi.setText(lichhen.getNamebs());
            txtgiokham.setText("Thời gian khám: " + lichhen.getKhunggiokham());
            txtnambenhnhan.setText("Tên bệnh nhân: " + lichhen.getNamebenhnhan());

            String avatarPath = lichhen.getAvatarbs();
            if (avatarPath != null && !avatarPath.isEmpty()) {
                File avatarFile = new File(imglichhen.getContext().getFilesDir(), avatarPath);
                if (avatarFile.exists()) {
                    Glide.with(imglichhen.getContext())
                            .load(avatarFile)
                            .apply(RequestOptions.circleCropTransform())
                            .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định trong khi tải
                            .into(imglichhen);
                } else {
                    // Nếu file không tồn tại, hiển thị ảnh mặc định
                    imglichhen.setImageResource(R.drawable.baseline_account_circle_24);
                }
            } else {
                // Nếu avatarPath là null hoặc rỗng, hiển thị ảnh mặc định
                imglichhen.setImageResource(R.drawable.baseline_account_circle_24);
            }

        }

    }
}
