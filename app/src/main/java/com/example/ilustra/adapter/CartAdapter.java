package com.example.ilustra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.ilustra.Helper.ChangeNumberItemsListener;
import com.example.ilustra.Helper.ManagmentCart;
import com.example.ilustra.databinding.ActivityViewholderCartBinding;
import com.example.ilustra.domain.PopularDomain;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder> {
    ArrayList<PopularDomain> items;
    Context context;
    ActivityViewholderCartBinding binding;
    ChangeNumberItemsListener changeNumberItemsListener;
    ManagmentCart managmentCart;

    public CartAdapter(ArrayList<PopularDomain> items, ChangeNumberItemsListener changeNumberItemsListener) {
        this.items = items;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ActivityViewholderCartBinding.inflate(LayoutInflater.from(context), parent, false);
        context = parent.getContext();
        managmentCart=new ManagmentCart(context);
        return new Viewholder(binding);
    }

   @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.feeEachItem.setText("$"+items.get(position).getPrice());
        binding.totalEachitem.setText("$"+Math.round(items.get(position).getNumberInCart()*items.get(position).getPrice()));
        binding.numberItemTxt.setText(String.valueOf(items.get(position).getNumberInCart()));

        int drawableResourced=holder.itemView.getResources().getIdentifier(items.get(position).getPicURL(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(binding.pic);


        binding.plusCartbtn.setOnClickListener(view -> managmentCart.plusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));

        binding.minusCartBtn.setOnClickListener(view -> managmentCart.minusNumberItem(items, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.change();
        }));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(ActivityViewholderCartBinding binding) {
            super(binding.getRoot());
        }
    }
    }




