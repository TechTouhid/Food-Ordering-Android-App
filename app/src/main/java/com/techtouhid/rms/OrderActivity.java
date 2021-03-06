package com.techtouhid.rms;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.techtouhid.rms.adapters.OrdersAdapter;
import com.techtouhid.rms.database.DBHelper;
import com.techtouhid.rms.databinding.ActivityOrderBinding;
import com.techtouhid.rms.models.OrdersModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

    }
}