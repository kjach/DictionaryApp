package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DictionaryActivity extends AppCompatActivity {
    Button goToDictionaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);

        goToDictionaryBtn = findViewById(R.id.goToDictionaryBtn);
        goToDictionaryBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.RoyalBlue)));

        goToDictionaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DictionaryActivity.this, SearchWordActivity.class);
                startActivity(intent);
            }
        });
    }
}