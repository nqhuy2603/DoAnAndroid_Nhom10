package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter.ItemAdapter;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseHelper;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseManager;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EditDataActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edName, edPrice, edMoTa, edCategory, edSoLuong, edID;
    Spinner spinnerImg;
    Button btnAdd, btnEdit, btnDelete, btnShow;
    ListView listViewEdit;
    private DatabaseManager dbmana;
    private DatabaseHelper dbhelper;
    private ItemAdapter itemAdapter;
    List<Item> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        dbmana = new DatabaseManager(EditDataActivity.this);
        dbhelper = new DatabaseHelper(EditDataActivity.this);

        AnhXa();
        ActionToolBar();

        List<String> imageList = dbmana.getImageList();
        // Create custom adapter for the Spinner
        ArrayAdapter<String> imageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, imageList);
        imageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerImg.setAdapter(imageAdapter);
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarEdit);
        edName = findViewById(R.id.editEditTenSanPham);
        edPrice = findViewById(R.id.editEditGiaSanPham);
        edMoTa = findViewById(R.id.editEditMoTaSanPham);
        edCategory = findViewById(R.id.editEditDanhMucSanPham);
        edSoLuong = findViewById(R.id.editEditSoLuongSanPham);
        edID = findViewById(R.id.editEditIDSanPham);
        spinnerImg = findViewById(R.id.editSpinnerImgSanPham);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new MyEvent());
        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new MyEvent());
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new MyEvent());
        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new MyEvent());
        listViewEdit = findViewById(R.id.listViewEdit);


    }
    private class MyEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if(v.getId()==R.id.btnAdd)
            {
                addSanPham();
            }
            else if(v.getId()==R.id.btnShow)
            {
                showSanPham();
            }
            else if(v.getId()==R.id.btnDelete)
            {
                deleteSanPham();
            }
            else if (v.getId()==R.id.btnEdit)
            {
                editSanPham();
            }
        }
    }

    private void addSanPham() {
        String name = edName.getText().toString().trim();
        String priceStr = edPrice.getText().toString().trim();
        String moTa = edMoTa.getText().toString().trim();
        String category = edCategory.getText().toString().trim();
        String soLuongStr = edSoLuong.getText().toString().trim();

        // Kiểm tra giá trị null cho các trường không được nhập
        Double price = priceStr.isEmpty() ? null : Double.parseDouble(priceStr);
        Integer soLuong = soLuongStr.isEmpty() ? null : Integer.parseInt(soLuongStr);
        // Retrieve the selected image from the Spinner

        String selectedImage = spinnerImg.getSelectedItem().toString();

        if (name.isEmpty() || moTa.isEmpty() || category.isEmpty()) {
            // Hiển thị thông báo nếu có trường nào đó không được nhập
            Toast.makeText(EditDataActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {


            // Tạo đối tượng Item từ thông tin nhập vào
            long categoryId = Long.parseLong(category);  // Gán categoryId của bạn tùy thuộc vào cách bạn lấy được categoryId
            long result = dbmana.insertItem(categoryId, name, price, 0, moTa, selectedImage);
//            String.valueOf(R.drawable.hinh_anh_san_pham_2)
            if (result > 0) {
                // Nếu thêm thành công, thông báo và làm mới danh sách sản phẩm
                Toast.makeText(EditDataActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                showSanPham(); // Làm mới danh sách sản phẩm để hiển thị sản phẩm mới thêm vào
            } else {
                // Nếu có lỗi khi thêm sản phẩm, thông báo lỗi
                Toast.makeText(EditDataActivity.this, "Lỗi khi thêm sản phẩm", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSanPham() {
        list = dbmana.getAllItems();
        itemAdapter = new ItemAdapter(EditDataActivity.this, list);
        listViewEdit.setAdapter(itemAdapter);
    }

    private void deleteSanPham() {
        // Lấy ID của sản phẩm muốn xóa từ EditText hoặc bất kỳ nguồn dữ liệu nào bạn đang sử dụng
        String itemIdStr = edID.getText().toString().trim();

        if (!itemIdStr.isEmpty()) {
            long itemId = Long.parseLong(itemIdStr);

            // Gọi phương thức xóa sản phẩm từ DatabaseManager
            dbmana.deleteItem(itemId);

            // Hiển thị thông báo xóa thành công
            Toast.makeText(EditDataActivity.this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();

            // Làm mới danh sách sản phẩm để hiển thị danh sách mới
            showSanPham();
        } else {
            // Hiển thị thông báo nếu ID sản phẩm không được nhập
            Toast.makeText(EditDataActivity.this, "Vui lòng nhập ID sản phẩm cần xóa", Toast.LENGTH_SHORT).show();
        }
    }
    private void editSanPham() {
        String itemIdStr = edID.getText().toString().trim();
        String name = edName.getText().toString().trim();
        String priceStr = edPrice.getText().toString().trim();
        String moTa = edMoTa.getText().toString().trim();
        String category = edCategory.getText().toString().trim();
        String soLuongStr = edSoLuong.getText().toString().trim();
        String selectedImage = spinnerImg.getSelectedItem().toString();

        // Kiểm tra giá trị null cho các trường không được nhập

        if (itemIdStr.isEmpty() || name.isEmpty() ||  category.isEmpty() || soLuongStr.isEmpty() || priceStr.isEmpty() || moTa.isEmpty()) {
            // Hiển thị thông báo nếu có trường nào đó không được nhập
            Toast.makeText(EditDataActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            Integer itemId = Integer.parseInt(itemIdStr);
            Integer price = Integer.parseInt(priceStr);
            Integer soLuong = Integer.parseInt(soLuongStr);
            Integer cateID = Integer.parseInt(category);
            // Gán categoryId của bạn tùy thuộc vào cách bạn lấy được categoryId
            long result = dbmana.updateItem(itemId, cateID, name, price, soLuong, moTa, selectedImage);
//            String.valueOf(R.drawable.hinh_anh_san_pham_2)
            if (result > 0) {
                // Nếu thêm thành công, thông báo và làm mới danh sách sản phẩm
                Toast.makeText(EditDataActivity.this, "Cập nhật sản phẩm thành công", Toast.LENGTH_SHORT).show();
                showSanPham(); // Làm mới danh sách sản phẩm để hiển thị sản phẩm mới thêm vào
            } else {
                // Nếu có lỗi khi thêm sản phẩm, thông báo lỗi
                Toast.makeText(EditDataActivity.this, "Lỗi khi cập nhật sản phẩm", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Tạo một biểu đồ vector cho nút back với màu trắng
        Drawable backArrow = ContextCompat.getDrawable(this, R.drawable.baseline_arrow_back_ios_new_24);
        if (backArrow != null) {
            backArrow = DrawableCompat.wrap(backArrow);
            DrawableCompat.setTint(backArrow, ContextCompat.getColor(this, android.R.color.white));
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}