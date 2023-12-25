package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private Context context;
    private List<Item> items;
    public ItemAdapter(Context context, List<Item> items) {
        super(context,0,items);
        this.context = context;
        this.items = items;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Kiểm tra xem view đã được tái sử dụng chưa, nếu chưa thì inflate layout mới
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view, parent, false);
        }

        // Lấy category tại vị trí position
        Item item = getItem(position);

        // Thiết lập dữ liệu vào các view trong item
        ImageView itemImage = convertView.findViewById(R.id.imgItem);
        TextView itemName = convertView.findViewById(R.id.nameItem);
        TextView itemPrice = convertView.findViewById(R.id.priceItem);

        itemImage.setImageResource(item.getImage()); // Đặt hình ảnh từ dữ liệu
        itemName.setText(item.getName()); // Đặt tên từ dữ liệu
        double price = item.getPrice();
        String formattedPrice = String.format("%,.0f đ", price);
        itemPrice.setText(formattedPrice);

        return convertView;
    }
}
