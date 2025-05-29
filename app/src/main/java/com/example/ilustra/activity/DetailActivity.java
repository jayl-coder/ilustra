package com.example.ilustra.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.ilustra.Helper.ManagmentCart;
import com.example.ilustra.R;
import com.example.ilustra.databinding.ActivityDetailBinding;
import com.example.ilustra.domain.PopularDomain;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private PopularDomain object;
    private int numberOrder = 1;
    private ManagmentCart managmentCart;
    private TextView highestBidTextView;
    private Button placeBidButton;
    private EditText bidAmountEditText;
    private double highestBid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getBundles();
        managmentCart = new ManagmentCart(this);
        statusBarColor();
    }

    private void statusBarColor() {
        Window window = DetailActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(DetailActivity.this, R.color.white));
    }

    private void getBundles() {
        object = (PopularDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPicURL(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(binding.itemPic);

        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("$" + object.getPrice());
        binding.descriptionTxt.setText(object.getDescription());
        binding.reviewTxt.setText(object.getReview() + "");
        binding.ratingTxt.setText(object.getScore() + "");

        binding.addToCardBtn.setOnClickListener(view -> {
            object.setNumberInCart(numberOrder);
            managmentCart.insertFood(object);
        });

        binding.backBtn.setOnClickListener(view -> finish());
    }

    private void setupBidding() {
        highestBidTextView = binding.highestBidTextView;
        bidAmountEditText = binding.bidAmountEditText;
        placeBidButton = binding.placeBidButton;

        placeBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bidInput = bidAmountEditText.getText().toString().trim();

                if (TextUtils.isEmpty(bidInput)) {
                    Toast.makeText(DetailActivity.this, "Please enter a bid amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                double bidValue;
                try {
                    bidValue = Double.parseDouble(bidInput);
                } catch (NumberFormatException e) {
                    Toast.makeText(DetailActivity.this, "Invalid bid amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (bidValue <= highestBid) {
                    Toast.makeText(DetailActivity.this, "Your bid must be higher than the current highest bid", Toast.LENGTH_SHORT).show();
                } else {
                    highestBid = bidValue;
                    highestBidTextView.setText(String.format("Highest Bid: $%.2f", highestBid));
                    Toast.makeText(DetailActivity.this, "Bid placed successfully!", Toast.LENGTH_SHORT).show();
                    bidAmountEditText.setText("");
                }
            }
        });
    }
}
