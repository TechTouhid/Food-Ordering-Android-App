package com.techtouhid.rms;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.techtouhid.rms.adapters.MainAdapter;
import com.techtouhid.rms.databinding.ActivityMainBinding;
import com.techtouhid.rms.models.MainModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Implemented view binding method
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.barger, "Burger", "5", "Chicken burger with extra sos"));
        list.add(new MainModel(R.drawable.pitzza, "Pizza", "10", "Pizza with extra chase so yummy"));
        list.add(new MainModel(R.drawable.chinese, "Noodles", "15", "Chinese noodles is a famous noodles"));
        list.add(new MainModel(R.drawable.thai_soup, "Thai", "12", "Thai Soup is very a very delicious soup"));
        list.add(new MainModel(R.drawable.friedchickenbiryani, "Chicken Biryani", "20", "Chicken Biryani is very famous"));
        list.add(new MainModel(R.drawable.kfc, "KFC Chicken", "20", "KFC Chicken is very famous in the world"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }

    @Override
   public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}