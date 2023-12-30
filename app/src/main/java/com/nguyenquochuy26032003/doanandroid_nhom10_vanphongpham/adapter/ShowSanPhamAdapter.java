package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseManager;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;

import java.util.List;

public class ShowSanPhamAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items;
    private DatabaseManager dbmana;
    public ShowSanPhamAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
        this.dbmana = new DatabaseManager(context);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_show_san_pham, parent, false);
        }

        // Thiết lập dữ liệu vào các view trong item
        TextView sanphamid = convertView.findViewById(R.id.tvShowIdSanPham);
        TextView sanphamname = convertView.findViewById(R.id.tvShowNameSanPham);
        TextView sanphamdanhmuc = convertView.findViewById(R.id.tvShowCateSanPham);
        TextView sanphamsoluong = convertView.findViewById(R.id.tvShowSoLuongSanPham);
        TextView sanphamprice = convertView.findViewById(R.id.tvShowPriceSanPham);
        TextView sanphammota = convertView.findViewById(R.id.tvShowMoTaSanPham);
        ImageView sanphamimg = convertView.findViewById(R.id.tvShowImgSanPham);

        sanphamid.setText(String.valueOf("id:" + items.get(position).getId())); // Đặt tên từ dữ liệu
        sanphamname.setText("Tên:" + items.get(position).getName());

        int categoryId = items.get(position).getIdc();
        String categoryName = dbmana.getCategoryNameById(categoryId);
        sanphamdanhmuc.setText("Danh mục: " + categoryName);


        sanphamsoluong.setText(String.valueOf("Số lượng:" + items.get(position).getQuantity()));
        double price = items.get(position).getPrice();
        String formattedPrice = String.format("%,.0f đ", price);
        sanphamprice.setText("Giá: "+formattedPrice);
        sanphammota.setText( "Mô tả: "+ items.get(position).getDescribe());
        sanphamimg.setImageResource(items.get(position).getImage());
        return convertView;
    }



}