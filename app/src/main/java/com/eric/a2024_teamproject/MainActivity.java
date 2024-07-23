package com.eric.a2024_teamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.eric.a2024_teamproject.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<FoodItem> items;
    private FoodAdapter adapter;
    private GridView gridView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
//        setContentView(R.layout.fragment_home);

        // 빈 데이터 리스트 생성
        items = new ArrayList<>();

        // GridView 설정
        gridView1 = findViewById(R.id.gridview1);
        adapter = new FoodAdapter(items, this);
        gridView1.setAdapter(adapter);

        // GridView1 아이템 클릭 시 수정 다이얼로그 표시
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showEditFoodDialog(position);
            }
        });

        // add_food 버튼 클릭 시 다이얼로그를 표시하여 항목을 추가하는 기능 구현
        Button addButton = findViewById(R.id.add_food);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddFoodDialog();
            }
        });

        // 검색 기능 구현
        androidx.appcompat.widget.SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void showAddFoodDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.adding_food, null);
        EditText nameEditText = dialogLayout.findViewById(R.id.food_name);
        EditText foodDateEditText = dialogLayout.findViewById(R.id.food_date);
        EditText foodEnddateEditText = dialogLayout.findViewById(R.id.food_enddate);

        builder.setTitle("Add Food");
        builder.setMessage("Enter the details of the food item");
        builder.setView(dialogLayout);
        builder.setPositiveButton("OK", (dialog, which) -> {
            String name = nameEditText.getText().toString();
            try {
                int foodDate = Integer.parseInt(foodDateEditText.getText().toString());
                int foodEnddate = Integer.parseInt(foodEnddateEditText.getText().toString());
                if (!name.isEmpty()) {
                    items.add(new FoodItem(name, foodDate, foodEnddate));
                    adapter.notifyDataSetChanged();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showEditFoodDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.adding_food, null);
        EditText nameEditText = dialogLayout.findViewById(R.id.food_name);
        EditText foodDateEditText = dialogLayout.findViewById(R.id.food_date);
        EditText foodEnddateEditText = dialogLayout.findViewById(R.id.food_enddate);

        FoodItem foodItem = items.get(position);
        nameEditText.setText(foodItem.getName());
        foodDateEditText.setText(String.valueOf(foodItem.getFoodDate()));
        foodEnddateEditText.setText(String.valueOf(foodItem.getFoodEnddate()));

        builder.setTitle("Edit Food");
        builder.setMessage("Modify the details of the food item");
        builder.setView(dialogLayout);
        builder.setPositiveButton("OK", (dialog, which) -> {
            String name = nameEditText.getText().toString();
            int foodDate = Integer.parseInt(foodDateEditText.getText().toString());
            int foodEnddate = Integer.parseInt(foodEnddateEditText.getText().toString());
            if (!name.isEmpty()) {
                items.set(position, new FoodItem(name, foodDate, foodEnddate));
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void filter(String text) {
        ArrayList<FoodItem> filteredList = new ArrayList<>();
        for (FoodItem item : items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.updateList(filteredList);
    }
}
