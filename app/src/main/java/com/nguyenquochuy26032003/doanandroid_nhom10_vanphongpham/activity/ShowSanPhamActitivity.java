package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter.ShowSanPhamAdapter;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseManager;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;

import java.util.ArrayList;
import java.util.List;

public class ShowSanPhamActitivity extends AppCompatActivity {
    Toolbar toolbar;
    private DatabaseManager dbmana;
    private ShowSanPhamAdapter showSanPhamAdapter;
    List<Item> list = new ArrayList<>();
    ListView listViewShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_san_pham_actitivity);
        AnhXa();
        ActionToolBar();
        dbmana = new DatabaseManager(ShowSanPhamActitivity.this);
        showSanPham();


    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarShow);
        listViewShow = findViewById(R.id.listViewShow);
    }

    private void showSanPham() {
        list = dbmana.getAllItems();
        showSanPhamAdapter = new ShowSanPhamAdapter(ShowSanPhamActitivity.this, list);
        listViewShow.setAdapter(showSanPhamAdapter);
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