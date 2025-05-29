package com.example.ilustra.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.ilustra.activity.DetailActivity;
import com.example.ilustra.databinding.ViewholderPupListBinding;
import com.example.ilustra.domain.PopularDomain;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {
    ArrayList<PopularDomain> items;
    Context context;
    ViewholderPupListBinding binding;

    public PopularAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderPupListBinding binding = ViewholderPupListBinding.inflate(LayoutInflater.from(context), parent, false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        binding.titletxt.setText(items.get(position).getTitle());
        binding.feetext.setText("$" + items.get(position).getPrice());
        binding.Scoretext.setText("" + items.get(position).getScore());
binding.reviewtext.setText(""+items.get(position).getReview());
        int drawableResourced=holder.itemView.getResources().getIdentifier(items.get(position).getPicURL(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(binding.pic);


        holder.itemView.setOnClickListener(view -> {
            Intent intent=new Intent(context, DetailActivity.class);
            intent.putExtra("object",items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(ViewholderPupListBinding binding) {
            super(binding.getRoot());
        }
    }
    }




