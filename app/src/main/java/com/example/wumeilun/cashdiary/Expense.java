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

public class Expense extends AppCompatActivity implements View.OnClickListener {

    private EditText expenseAmount;
    private EditText expenseDate;
    private EditText expenseComment;
    private Button expenseCancel;
    private Button expenseAdd;
    private TextView totalExpense;
    private ListView expenseList;

    private ContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense);

        expenseAmount = findViewById(R.id.expenseAmount);
        expenseDate = findViewById(R.id.expenseDate);
        expenseComment = findViewById(R.id.expenseComment);
        expenseCancel = findViewById(R.id.expenseCancel);
        expenseAdd = findViewById(R.id.expenseAdd);
        totalExpense = findViewById(R.id.totalExpense);
        expenseList = findViewById(R.id.expenseList);

        expenseCancel.setOnClickListener(this);
        expenseAdd.setOnClickListener(this);

        adapter = new ContentAdapter(Expense.this, MainActivity.expenseList);
        expenseList.setAdapter(adapter);

        // Refresh the totalExpense TextView
        RefreshTotalExpense();
    }

    // Refresh the totalExpense TextView
    private void RefreshTotalExpense() {
        int totalExpenseNum = 0;
        for (int i = 0; i < MainActivity.expenseList.size(); i++) {
            String expenseStr = (String) MainActivity.expenseList.get(i).get("amount");
            // Remove the beginning symbol "-"
            expenseStr = expenseStr.substring(1);
            int expense = Integer.valueOf(expenseStr);
            totalExpenseNum += expense;
        }
        totalExpense.setText("$-" + totalExpenseNum);
    }

    @Override
    public void onClick(View view) {
        if (view == expenseCancel) {
            expenseAmount.setText(null);
            expenseDate.setText(null);
            expenseComment.setText(null);
        } else if (view == expenseAdd) {
            String amountStr = expenseAmount.getText().toString();
            String dateStr = expenseDate.getText().toString();
            String commentStr = expenseComment.getText().toString();

            // Determine whether there is empty string
            if ((TextUtils.isEmpty(amountStr)) || (TextUtils.isEmpty(dateStr)) || (TextUtils.isEmpty(commentStr))) {
                Toast.makeText(this, "Please complete the information!", Toast.LENGTH_SHORT).show();
            } else {
                // User cannot add if there are more than 50 records
                if (MainActivity.expenseList.size() >= 50) {
                    Toast.makeText(this, "You can only add 50 records!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Add expense successfully!", Toast.LENGTH_SHORT).show();

                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("amount", "-" + amountStr);
                    map.put("date", dateStr);
                    map.put("comment", commentStr);
                    MainActivity.expenseList.add(map);
                    MainActivity.totalList.add(map);

                    // Refresh the ListView
                    adapter = new ContentAdapter(Expense.this, MainActivity.expenseList);
                    expenseList.setAdapter(adapter);

                    // Refresh the totalexpense TextView
                    RefreshTotalExpense();
                }
            }
        }
    }
}