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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<FoodItem> items; // 식품 항목을 저장하는 ArrayList
    private FoodAdapter adapter; // GridView에 사용되는 어댑터
    private GridView gridView1; // GridView 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

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
                showEditFoodDialog(position); // 항목을 클릭하면 수정 다이얼로그 표시
            }
        });

        // add_food 버튼 클릭 시 다이얼로그를 표시하여 항목을 추가하는 기능 구현
        Button addButton = findViewById(R.id.add_food);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddFoodDialog(); // 추가 버튼 클릭 시 추가 다이얼로그 표시
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
                filter(newText); // 검색어 변경 시 필터링 수행
                return false;
            }
        });
    }

    // 음식 추가 다이얼로그 표시
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
                    int imageResId = getImageResourceId(name); // 음식 이름에 따라 이미지 리소스 ID 가져오기
                    items.add(new FoodItem(name, foodDate, foodEnddate, imageResId)); // 새로운 FoodItem 추가
                    adapter.notifyDataSetChanged(); // 어댑터에 데이터 변경 알림
                }
            } catch (NumberFormatException e) {
                e.printStackTrace(); // 숫자 형식 예외 처리
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    // 음식 수정 다이얼로그 표시
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
                int imageResId = getImageResourceId(name); // 음식 이름에 따라 이미지 리소스 ID 가져오기
                items.set(position, new FoodItem(name, foodDate, foodEnddate, imageResId)); // 수정된 FoodItem 설정
                adapter.notifyDataSetChanged(); // 어댑터에 데이터 변경 알림
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    // 음식 이름에 따라 이미지 리소스 ID 반환
    private int getImageResourceId(String foodName) {
        switch (foodName.toLowerCase()) {
            case "양파":
                return R.drawable.onion;
            // Add more cases for different food items and their corresponding images



            default:
                return R.drawable.apple; // 일치하는 음식이 없을 경우 기본 이미지 반환
        }
    }

    // 검색어에 따라 목록 필터링
    private void filter(String text) {
        ArrayList<FoodItem> filteredList = new ArrayList<>();
        for (FoodItem item : items) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item); // 검색어가 포함된 항목만 추가
            }
        }
        adapter.updateList(filteredList); // 필터링된 목록으로 어댑터 업데이트
    }
}
