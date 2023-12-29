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
    EditText edName, edPrice, edMoTa, edCategory, edSoLuong;
    Button btnAdd, btnEdit, btnDelete, btnShow;
    ListView listViewEdit;
    private DatabaseManager dbmana;
    private DatabaseHelper dbhelper;
    private ItemAdapter itemAdapter;
    List<Item> list = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        dbmana = new DatabaseManager(EditDataActivity.this);
        dbhelper = new DatabaseHelper(EditDataActivity.this);

        AnhXa();
        ActionToolBar();
        ActionToolBar();

    }


    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarEdit);
        edName = findViewById(R.id.editEditTenSanPham);
        edPrice = findViewById(R.id.editEditGiaSanPham);
        edMoTa = findViewById(R.id.editEditMoTaSanPham);
        edCategory = findViewById(R.id.editEditDanhMucSanPham);
        edSoLuong = findViewById(R.id.editEditSoLuongSanPham);
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
    }

    private void showSanPham() {
        list = dbmana.getAllItems();
        itemAdapter = new ItemAdapter(EditDataActivity.this, list);
        listViewEdit.setAdapter(itemAdapter);
    }

    private void deleteSanPham() {

    }
    private void editSanPham() {

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