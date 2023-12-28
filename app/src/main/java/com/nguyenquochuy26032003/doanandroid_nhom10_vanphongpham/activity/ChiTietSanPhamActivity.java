package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    TextView name, priceItem, mota;
    Button btnThem;
    ImageView imgHinh;
    Spinner spinner;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        AnhXa();
        ActionToolBar();
        getData();
    }

    private void getData() {
        Item item = (Item) getIntent().getSerializableExtra("chitiet");
        name.setText(item.getName());
        mota.setText(item.getDescribe());
        Glide.with(getApplicationContext()).load(item.getImage()).into(imgHinh);
        double price = item.getPrice();
        String formattedPrice = String.format("%,.0f đ", price);
        priceItem.setText(formattedPrice);
    }

    private void AnhXa() {
        name = findViewById(R.id.tvTenSanPham);
        priceItem = findViewById(R.id.tvGiaSanPham);
        mota = findViewById(R.id.tvMoTaSanPham);
        btnThem = findViewById(R.id.btnThemVaoGioHang);
        imgHinh = findViewById(R.id.imgChiTiet);
        spinner = findViewById(R.id.spinnerSanPham);
        toolbar = findViewById(R.id.toolbarChiTietSach);
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