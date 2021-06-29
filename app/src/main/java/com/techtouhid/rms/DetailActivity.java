package com.techtouhid.rms;

import android.content.Intent;
import android.database.Cursor;
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

        DBHelper helper = new DBHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(binding.quantity.getText().toString());;
                    quantity += 1;
                    binding.quantity.setText(String.format("%d", quantity));
                    binding.priceLbl.setText(String.format("%d", price * quantity));
                }
            });

            binding.subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(binding.quantity.getText().toString());
                    if (quantity > 0) {
                        quantity = quantity - 1;
                        binding.quantity.setText(String.format("%d", quantity));
                        int subtract_price = Integer.parseInt(binding.priceLbl.getText().toString());
                        binding.priceLbl.setText(String.format("%d", subtract_price - price));
                    }
                }
            });

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.orderdFoodName.setText(name);
            binding.detailDescription.setText(description);

            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            description,
                            name
                    );

                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Order Placed Successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4);

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.priceLbl.setText(String.format("%d", cursor.getInt(3)));
            binding.detailImage.setImageResource(image);
            binding.quantity.setText(cursor.getString(5));
            binding.detailDescription.setText(cursor.getString(6));
            binding.orderdFoodName.setText(cursor.getString(7));
            binding.insertButton.setText("Update Now");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = helper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            binding.detailDescription.getText().toString(),
                            binding.orderdFoodName.getText().toString(),
                            id
                    );
                    if (isUpdated) {
                        Toast.makeText(DetailActivity.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }
    }
}