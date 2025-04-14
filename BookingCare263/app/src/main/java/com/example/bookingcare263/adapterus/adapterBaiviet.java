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
import com.example.bookingcare263.FirebaseCallBack;
import com.example.bookingcare263.FirebaseHelper;
import com.example.bookingcare263.R;
import com.example.bookingcare263.model.Bacsi;
import com.example.bookingcare263.model.Baiviet;
import com.example.bookingcare263.ui.Xuly;
import com.example.bookingcare263.UserActivity;
import com.example.bookingcare263.ui.adminui.AdminActivity;

import java.util.ArrayList;

public class adapterBaiviet extends RecyclerView.Adapter<adapterBaiviet.ViewHolder> {
    ArrayList <Baiviet> listbaiviet;
    private setItemClick listener;
    public void setOnItemClickListener(setItemClick listener) {
        this.listener = listener;
    }
    public adapterBaiviet(ArrayList<Baiviet> listbaiviet) {
        this.listbaiviet = listbaiviet;
    }

    public interface setItemClick{
        void onItemClick(Baiviet baiviet);
        void onImageavatarClick(Baiviet baiviet);
        void onItemDatkhamClick(Baiviet baiviet);
        void onItemDeleteClick(Baiviet baiviet);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_baiviet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baiviet baiviet = listbaiviet.get(position);
        holder.bind(baiviet);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(baiviet);
            }

        });
        holder.txtnametbvshow.setOnClickListener(v -> {
            if (listener != null) {
                listener.onImageavatarClick(baiviet);
            }
            });
        holder.btndatkham.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemDatkhamClick(baiviet);
            }
        });
        holder.imgdeletebv.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemDeleteClick(baiviet);
            }
        });



    }

    @Override
    public int getItemCount() {
        return listbaiviet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgavatartbvshow, imganhbaivietshow, imgdeletebv;
        TextView txtnametbvshow, txtitleshowbv, txtcontentbv, txtxemthembv, txttimetbvshow;
        Button btndatkham;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgavatartbvshow = itemView.findViewById(R.id.imgavatartbvshowitem);
            imganhbaivietshow = itemView.findViewById(R.id.imganhbaivietshowitem);
            txtnametbvshow = itemView.findViewById(R.id.txtnametbvshowitem);
            txtitleshowbv = itemView.findViewById(R.id.txtitleshowbvitem);
            txtcontentbv = itemView.findViewById(R.id.txtcontentbvitem);
            txtxemthembv = itemView.findViewById(R.id.txtxemthembv);
            txttimetbvshow = itemView.findViewById(R.id.txttimetbvshowitem);
            btndatkham = itemView.findViewById(R.id.btndlk);
            imgdeletebv = itemView.findViewById(R.id.imgdeletebv);


        }
        public void bind(Baiviet baiviet){
            // getbac by iduser
            FirebaseHelper.getBacsiBySdt(baiviet.getIduser(), new FirebaseCallBack<Bacsi>() {
                @Override
                public void onSuccess(Bacsi data) {

                    txtnametbvshow.setText(data.getName());
                    String avatarUri = data.getImg();
                    if (avatarUri != null && !avatarUri.isEmpty()) {
                        Glide.with(imgavatartbvshow.getContext())
                                .load(Uri.parse(avatarUri)) // Chuyển String thành Uri
                                .into(imgavatartbvshow);
                    } else {
                        imgavatartbvshow.setImageResource(R.drawable.baseline_account_circle_24);
                    }

                }

                @Override
                public void onFailed(String message) {

                }
            });



            if(UserActivity.iduser != null || baiviet.getIduser().equals(UserActivity.roleuser)
                    || "admin".equals(AdminActivity.roleadmin) ){
                imgdeletebv.setVisibility(View.VISIBLE);
            } else {
                imgdeletebv.setVisibility(View.GONE);
            }

            if(UserActivity.roleuser != null && UserActivity.roleuser.equals("user")) {
                btndatkham.setVisibility(View.VISIBLE);
            } else {
                btndatkham.setVisibility(View.GONE);
            }



            txtitleshowbv.setText(baiviet.getTitile());
            txtcontentbv.setText(baiviet.getContent());
           // neu so dòng content lớn hơn 3 thì ẩn nút xem thêm
            if (txtcontentbv.getLineCount() > 3) {
                txtxemthembv.setVisibility(View.VISIBLE);
            } else {
                txtxemthembv.setVisibility(View.GONE);
            }

            txtxemthembv.setOnClickListener(v -> {
                if (txtcontentbv.getMaxLines() == 3) {
                    txtcontentbv.setMaxLines(Integer.MAX_VALUE); // Hiển thị toàn bộ nội dung
                    txtxemthembv.setText("Thu gọn");
                } else {
                    txtcontentbv.setMaxLines(3); // Giới hạn lại
                    txtxemthembv.setText("Xem thêm");
                }
            });
            String texttime = Xuly.getRelativeTime(baiviet.getTimestamp());
            txttimetbvshow.setText(texttime);

            String anhbaiviet = baiviet.getImg();
            if (anhbaiviet != null && !anhbaiviet.isEmpty()) {
                Glide.with(imganhbaivietshow.getContext())
                        .load(Uri.parse(anhbaiviet)) // Chuyển String thành Uri
                        .into(imganhbaivietshow);
            } else {
                imganhbaivietshow.setVisibility(View.GONE);
                imganhbaivietshow.setImageResource(R.drawable.baseline_account_circle_24);
            }
        }



    }
}
