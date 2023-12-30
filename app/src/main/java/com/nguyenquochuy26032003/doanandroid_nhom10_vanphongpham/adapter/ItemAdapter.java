package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.Interface.ItemClickListener;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items;
    public ItemAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Kiểm tra xem view đã được tái sử dụng chưa, nếu chưa thì inflate layout mới
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view, parent, false);
        }

        // Thiết lập dữ liệu vào các view trong item
        ImageView itemImage = convertView.findViewById(R.id.imgItem);
        TextView itemName = convertView.findViewById(R.id.nameItem);
        TextView itemPrice = convertView.findViewById(R.id.priceItem);

        itemImage.setImageResource(items.get(position).getImage());
        itemName.setText(items.get(position).getName()); // Đặt tên từ dữ liệu
        double price = items.get(position).getPrice();
        String formattedPrice = String.format("%,.0f đ", price);
        itemPrice.setText("Giá: "+formattedPrice);

        return convertView;
    }
}
