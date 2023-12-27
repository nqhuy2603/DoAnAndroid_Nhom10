package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter.SachAdapter;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseManager;

public class SachActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;

    SachAdapter sachAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);

        AnhXa();
        ActionToolBar();
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarSach);
        recyclerView = findViewById(R.id.recyclerviewSach);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

}