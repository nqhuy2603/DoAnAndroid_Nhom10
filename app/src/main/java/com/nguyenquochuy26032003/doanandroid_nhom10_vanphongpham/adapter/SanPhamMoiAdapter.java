package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;

import java.util.List;

//CÁI NÀY CODE ĐỂ ĐÓ TEST SAU
public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.MyViewHolder> {
    private Context context;
    private List<Item> array;

    public SanPhamMoiAdapter(Context context, List<Item> array) {
        this.context = context;
        this.array = array;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemPrice, itemName;
        ImageView itemImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPrice = itemView.findViewById(R.id.priceItem);
            itemName = itemView.findViewById(R.id.nameItem);
            itemImage = itemView.findViewById(R.id.imgItem);
        }
    }
    @NonNull
    @Override
    public SanPhamMoiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamMoiAdapter.MyViewHolder holder, int position) {
        Item item = array.get(position);
        holder.itemName.setText(item.getName());
        double price = item.getPrice();
        String formattedPrice = String.format("%,.0f đ", price);
        holder.itemPrice.setText(formattedPrice);
        Glide.with(context).load(item.getImage()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


}
