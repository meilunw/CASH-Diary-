package com.example.wumeilun.cashdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button balance;
    private Button income;
    private Button expense;
    private Button compare;

    static ArrayList<Map<String, Object>> incomeList;
    static ArrayList<Map<String, Object>> expenseList;
    static ArrayList<Map<String, Object>> totalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        balance =findViewById(R.id.balance);
        income = findViewById(R.id.income);
        expense = findViewById(R.id.expense);
        compare = findViewById(R.id.compare);

        balance.setOnClickListener(this);
        income.setOnClickListener(this);
        expense.setOnClickListener(this);
        compare.setOnClickListener(this);

        incomeList = new ArrayList<Map<String, Object>>();
        expenseList = new ArrayList<Map<String, Object>>();
        totalList = new ArrayList<Map<String, Object>>();
    }

    @Override
    public void onClick(View view) {
        if (view == balance) {
            Intent intent = new Intent(this, Balance.class);
            startActivity(intent);
        } else if (view == income) {
            Intent intent = new Intent(this, Income.class);
            startActivity(intent);
        } else if (view == expense) {
            Intent intent = new Intent(this, Expense.class);
            startActivity(intent);
        } else if (view  == compare) {
            Intent intent = new Intent(this, Compare.class);
            startActivity(intent);
        }
    }}