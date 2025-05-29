package com.example.ilustra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ilustra.R;
import com.example.ilustra.adapter.PopularAdapter;
import com.example.ilustra.databinding.ActivityMainBinding;
import com.example.ilustra.domain.PopularDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        statusBarColor();
        initRecyclerView();
        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.cartBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,CartActivity.class)));
    }

    private void statusBarColor() {
        Window window=MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.purple_Dark));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items=new ArrayList<>();
        items.add(new PopularDomain("T-shirt black","item_1",15,4,500,"Immerse yourself in a world of vibrant and\n" +
                " immersive sound with the monitor.\n" +
                " Its cutting-edge monitortechnology brings every\n" +
                " scene to life with striking clarity and rich colors.\n" +
                " With the seamless integration and a sleek, modern\n" +
                " not just a TV, but a centerpiece for your\n" +
                " entertainment space . The ultra-slim bazel and \n" +
                " premium finish blend seamlessly with any decor"));
        items.add(new PopularDomain("Smart Watch","item_2",10,4.5,450,"Immerse yourself in a world of vibrant and\n" +
                " immersive sound with the monitor.\n" +
                " Its cutting-edge monitortechnology brings every\n" +
                " scene to life with striking clarity and rich colors.\n" +
                " With the seamless integration and a sleek, modern\n" +
                " not just a TV, but a centerpiece for your\n" +
                " entertainment space . The ultra-slim bazel and \n" +
                " premium finish blend seamlessly with any decor"));
        items.add(new PopularDomain("Phone","item_3",3,4.9,800,"Immerse yourself in a world of vibrant and\n" +
                " immersive sound with the monitor.\n" +
                " Its cutting-edge monitortechnology brings every\n" +
                " scene to life with striking clarity and rich colors.\n" +
                " With the seamless integration and a sleek, modern\n" +
                " not just a TV, but a centerpiece for your\n" +
                " entertainment space . The ultra-slim bazel and \n" +
                " premium finish blend seamlessly with any decor"));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.PopularView.setAdapter(new PopularAdapter(items));

    }
}