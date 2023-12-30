package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter.ItemAdapter;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter.SachAdapter;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseHelper;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseManager;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;

import java.util.List;

public class DungCuHocTapActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    private DatabaseManager dbmana;
    private DatabaseHelper dbhelper;
    private List<Item> items;

    private SachAdapter sachAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dung_cu_hoc_tap);

        AnhXa();
        ActionToolBar();
        ListViewItem();
    }

    private void ListViewItem() {
        dbmana = new DatabaseManager(this);
        dbhelper = new DatabaseHelper(this);

        // Lấy danh sách Category từ cơ sở dữ liệu
        items = dbmana.getItems(4);

        // Khởi tạo và đặt Adapter cho ListView
        sachAdapter = new SachAdapter(this, items);
        recyclerView.setAdapter(sachAdapter);
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

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarDungCuHocTap);
        recyclerView = findViewById(R.id.recyclerviewDungCuHocTap);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

}