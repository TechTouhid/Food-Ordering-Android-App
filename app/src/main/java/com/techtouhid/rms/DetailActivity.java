package com.techtouhid.rms;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.techtouhid.rms.database.DBHelper;
import com.techtouhid.rms.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final int image = getIntent().getIntExtra("image", 0);
        final int price = Integer.parseInt(getIntent().getStringExtra("price"));
        final String name = getIntent().getStringExtra("name");
        final String description = getIntent().getStringExtra("desc");

        binding.detailImage.setImageResource(image);
        binding.priceLbl.setText(String.format("%d", price));
        binding.orderdFoodName.setText(name);
        binding.detailDescription.setText(description);

        DBHelper helper = new DBHelper(this);

        binding.insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = helper.insertOrder(
                        binding.nameBox.getText().toString(),
                        binding.phoneBox.getText().toString(),
                        price,
                        image,
                        Integer.parseInt(binding.quantity.getText().toString()),
                        description,
                        name
                        );

                if (isInserted) {
                    Toast.makeText(DetailActivity.this, "Data Susses", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}