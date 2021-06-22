package com.techtouhid.rms;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.techtouhid.rms.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int image = getIntent().getIntExtra("image", 0);
        int price = Integer.parseInt(getIntent().getStringExtra("price"));
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("desc");

        binding.detailImage.setImageResource(image);
        binding.priceLbl.setText(String.format("%d", price));
        binding.orderdFoodName.setText(name);
        binding.detailDescription.setText(description);
    }
}