package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewTrangChu;
    NavigationView navigationView;
    ListView listViewTrangChu;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
//      Gọi phương thức actionBar
        ActionBar();
//      Gọi phương thức ActionViewFilipper(): nội dung thanh trượt
        ActionViewFilipper();

    }

    private void ActionViewFilipper() {
        List<Integer> arrayViewFlipper = new ArrayList<>();

        int image1Id = getResources().getIdentifier("banner_1", "drawable", getPackageName());
        int image2Id = getResources().getIdentifier("banner_2", "drawable", getPackageName());
        int image3Id = getResources().getIdentifier("banner_3", "drawable", getPackageName());

        // Thêm ID vào danh sách
        arrayViewFlipper.add(image1Id);
        arrayViewFlipper.add(image2Id);
        arrayViewFlipper.add(image3Id);

        for (int i = 0; i < arrayViewFlipper.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrayViewFlipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation silde_to = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.silde_to_right);
        Animation silde_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(silde_to);
        viewFlipper.setOutAnimation(silde_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolBarTrangChu);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerViewTrangChu = findViewById(R.id.recyclerview);
        navigationView = findViewById(R.id.navigationView);
        listViewTrangChu= findViewById(R.id.listViewTrangChu);
        drawerLayout = findViewById(R.id.drawerLayout);
    }


    private void ExitApp() {
        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setTitle("Thông Báo")
                .setMessage("Bạn có muốn thoát ứng dụng không?")
                .setCancelable(false)
                .setPositiveButton("Có", (dialog, which) -> {
                    finish();
                })
                .setNegativeButton("Không", (dialog, which) -> {
                    dialog.dismiss();
                });
        exit.create().show();
    }
}