package com.example.wumeilun.cashdiary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class Balance extends AppCompatActivity {
    private TextView totalBalance;
    private ListView balanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance);

        totalBalance = findViewById(R.id.totalBalance);
        balanceList = findViewById(R.id.balanceList);

        ContentAdapter adapter = new ContentAdapter(Balance.this, MainActivity.totalList);
        balanceList.setAdapter(adapter);

        // Refresh the totalBalance TextView
        RefreshTotalBalance();
    }

    // Refresh the totalBalance TextView
    private void RefreshTotalBalance() {
        int totalBalanceNum = 0;
        for (int i = 0; i < MainActivity.totalList.size(); i++) {
            String balanceStr = (String) MainActivity.totalList.get(i).get("amount");
            // Get the first symbol
            String symbol = balanceStr.substring(0, 1);
            // Remove the beginning symbol "+" or "-"
            balanceStr = balanceStr.substring(1);
            int balance = Integer.valueOf(balanceStr);
            if (symbol.equals("-")) {
                balance = -balance;
            }
            totalBalanceNum += balance;
        }

        if (totalBalanceNum > 0) {
            totalBalance.setText("$+" + totalBalanceNum);
        } else {
            totalBalance.setText("$" + totalBalanceNum);
        }
    }
}
