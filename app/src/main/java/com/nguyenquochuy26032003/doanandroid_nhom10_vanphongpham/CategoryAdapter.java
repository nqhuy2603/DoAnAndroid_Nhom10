package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        super(context, 0, categories);
        this.context = context;
        this.categories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Kiểm tra xem view đã được tái sử dụng chưa, nếu chưa thì inflate layout mới
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.categories_list_view, parent, false);
        }

        // Lấy category tại vị trí position
        Category category = getItem(position);

        // Thiết lập dữ liệu vào các view trong item
        ImageView categoryImage = convertView.findViewById(R.id.ImageOfCategory);
        TextView categoryName = convertView.findViewById(R.id.LabelOfCatergory);

        categoryImage.setImageResource(category.getImage()); // Đặt hình ảnh từ dữ liệu
        categoryName.setText(category.getName()); // Đặt tên từ dữ liệu

        return convertView;
    }
}
