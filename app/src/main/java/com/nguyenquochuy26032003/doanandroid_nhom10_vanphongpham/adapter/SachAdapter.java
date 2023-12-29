package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.Interface.ItemClickListener;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.activity.ChiTietSanPhamActivity;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;

import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.MyViewHolder> {
    Context context;
    List<Item> array;

    public SachAdapter(Context context, List<Item> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_sach, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = array.get(position);
        if (item != null) {
            holder.nameItem.setText(item.getName());
            double price = item.getPrice();
            String formattedPrice = String.format("%,.0f đ", price);
            holder.priceItem.setText(formattedPrice);
            Glide.with(context).load(item.getImage()).into(holder.imageItem);
            holder.describeItem.setText(item.getDescribe());
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if (!isLongClick) {
                        //click
                        Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                        intent.putExtra("chitiet", item);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        } else {
            // Xử lý khi item là null, có thể là hiển thị một giá trị mặc định hoặc làm gì đó phù hợp với ứng dụng
        }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameItem, priceItem, describeItem;
        ImageView imageItem;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameItem = itemView.findViewById(R.id.textviewNameItemSach);
            priceItem = itemView.findViewById(R.id.textviewPriceItemSach);
            describeItem = itemView.findViewById(R.id.textviewDescribeItemSach);
            imageItem = itemView.findViewById(R.id.imageItemSach);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
