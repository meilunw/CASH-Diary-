package com.example.wumeilun.cashdiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Income extends AppCompatActivity implements View.OnClickListener {
    private EditText incomeAmount;
    private EditText incomeDate;
    private EditText incomeComment;
    private Button incomeCancel;
    private Button incomeAdd;
    private TextView totalIncome;
    private ListView incomeList;

    private ContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income);

        incomeAmount = findViewById(R.id.incomeAmount);
        incomeDate = findViewById(R.id.incomeDate);
        incomeComment = findViewById(R.id.incomeComment);
        incomeCancel = findViewById(R.id.incomeCancel);
        incomeAdd = findViewById(R.id.incomeAdd);
        totalIncome = findViewById(R.id.totalIncome);
        incomeList = findViewById(R.id.incomeList);

        incomeCancel.setOnClickListener(this);
        incomeAdd.setOnClickListener(this);

        adapter = new ContentAdapter(Income.this, MainActivity.incomeList);
        incomeList.setAdapter(adapter);

        // Refresh the totalIncome TextView
        RefreshTotalIncome();
    }

    // Refresh the totalIncome TextView
    private void RefreshTotalIncome() {
        int totalIncomeNum = 0;
        for (int i = 0; i < MainActivity.incomeList.size(); i++) {
            String incomeStr = (String) MainActivity.incomeList.get(i).get("amount");
            // Remove the beginning symbol "+"
            incomeStr = incomeStr.substring(1);
            int income = Integer.valueOf(incomeStr);
            totalIncomeNum += income;
        }
        totalIncome.setText("$+" + totalIncomeNum);
    }

    @Override
    public void onClick(View view) {
        if (view == incomeCancel) {
            incomeAmount.setText(null);
            incomeDate.setText(null);
            incomeComment.setText(null);
        } else if (view == incomeAdd) {
            String amountStr = incomeAmount.getText().toString();
            String dateStr = incomeDate.getText().toString();
            String commentStr = incomeComment.getText().toString();

            // Determine whether there is empty string
            if ((TextUtils.isEmpty(amountStr)) || (TextUtils.isEmpty(dateStr)) || (TextUtils.isEmpty(commentStr))) {
                Toast.makeText(this, "Please complete the information!", Toast.LENGTH_SHORT).show();
            } else {
                // User cannot add if there are more than 50 records
                if (MainActivity.incomeList.size() >= 50) {
                    Toast.makeText(this, "You can only add 50 records!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Add income successfully!", Toast.LENGTH_SHORT).show();

                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("amount", "+" + amountStr);
                    map.put("date", dateStr);
                    map.put("comment", commentStr);
                    MainActivity.incomeList.add(map);
                    MainActivity.totalList.add(map);

                    // Refresh the ListView
                    adapter = new ContentAdapter(Income.this, MainActivity.incomeList);
                    incomeList.setAdapter(adapter);

                    // Refresh the totalIncome TextView
                    RefreshTotalIncome();
                }
            }
        }
    }
}
