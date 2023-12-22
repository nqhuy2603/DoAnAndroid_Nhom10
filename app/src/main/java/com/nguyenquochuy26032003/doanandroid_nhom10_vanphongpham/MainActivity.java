package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewTrangChu;
    NavigationView navigationView;
    ListView listViewTrangChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    private void AnhXa() {
        toolbar = findViewById(R.id.toolBarTrangChu);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerViewTrangChu = findViewById(R.id.recyclerview);
        navigationView = findViewById(R.id.navigationView);
        listViewTrangChu= findViewById(R.id.listViewTrangChu);
    }
}