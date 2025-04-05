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
import com.example.bookingcare263.model.accout;
import com.example.bookingcare263.model.benhnhan;

import java.util.ArrayList;

public class adapterAccout extends  RecyclerView.Adapter<adapterAccout.ViewHolder>{
    ArrayList<accout> list;
    public adapterAccout(ArrayList<accout> list) {
        this.list = list;
    }
    private onClickListener listener;
    public void setonListener(onClickListener listener){
        this.listener = listener;
    }
    public interface onClickListener{
        void onFixClick(int position);
        void onDeleteClick(int position);
        void onStatusLongClick(int position);
        void onItemClick(int position);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accout,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        accout accout = list.get(position);
        holder.setdata(accout);
        holder.imgfixaccout.setOnClickListener(v->{
            if (listener != null){
                listener.onFixClick(position);
            }
        });
        holder.imgdeleteaccout.setOnClickListener(v->{
            if (listener != null){
                listener.onDeleteClick(position);
            }
        });
        holder.imgstatus.setOnLongClickListener(v->{
            if (listener != null){
                listener.onStatusLongClick(position);
            }
            return true;
        });
        holder.itemView.setOnClickListener(v->{
            if (listener != null){
                listener.onItemClick(position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgaccout, imgstatus, imgfixaccout, imgdeleteaccout;
        TextView txttenaccout, txtsdtaccout, txtstatusaccout;
        DatabaseHelper helper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgaccout = itemView.findViewById(R.id.imgaccout);
            imgstatus = itemView.findViewById(R.id.imgstatus);
            imgfixaccout = itemView.findViewById(R.id.imgfixaccout);
            imgdeleteaccout = itemView.findViewById(R.id.imgdeleteaccout);
            txttenaccout = itemView.findViewById(R.id.txttenaccout);
            txtsdtaccout = itemView.findViewById(R.id.txtsdtaccout);
            txtstatusaccout = itemView.findViewById(R.id.txtstatusaccout);
            helper = new DatabaseHelper(itemView.getContext());
        }
        public void setdata(accout accout){

            // get status by sdt, role
            String name = accout.getName();
            String role = accout.getAs();
            String sdt = accout.getPhone();
            String status = accout.getStatus();
            String avatar ;
            if(role.equals("bacsi")){
                // get bacsi by sdt;
                Bacsi bacsi = helper.getBacsiBySdt(sdt);
                avatar = bacsi.getImg();
            } else {
                // get benhh nhan by sdt;
                benhnhan bn = helper.getbenhnhan(sdt);
                avatar = bn.getImg();
            }

            txttenaccout.setText(name);
            txtsdtaccout.setText(sdt);
            txtstatusaccout.setText(status);
            if(avatar != null &&  !avatar.isEmpty())
                Glide.with(imgaccout.getContext())
                    .load(Uri.parse(avatar)) // Chuyển String thành ặc đUri
                    .circleCrop()
                    .placeholder(R.drawable.baseline_account_circle_24) // Ảnh mặc định nếu đang load
                    .error(R.drawable.baseline_account_circle_24) // Ảnh mịnh nếu load thất bại
                    .into(imgaccout);
            else{
                imgaccout.setImageResource(R.drawable.baseline_account_circle_24);
            }
            if (status.equals("Đang hoạt đông")){
                imgstatus.setImageResource(R.drawable.check_mark);
            } else if (status.equals("Chờ duyệt")){
                imgstatus.setImageResource(R.drawable.dangduyet);
            } else {
                imgstatus.setImageResource(R.drawable.tamkhoa);
            }


        }
    }
}
