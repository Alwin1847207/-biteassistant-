package com.example.biteassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn = (Button)findViewById(R.id.button_gen);
        EditText editText_1 = (EditText)findViewById(R.id.text_1);
        EditText editText_2 = (EditText)findViewById(R.id.text_2);
        EditText editText_3 = (EditText)findViewById(R.id.text_3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }
}