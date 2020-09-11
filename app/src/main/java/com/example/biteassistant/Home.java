package com.example.biteassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {
    EditText editText_1,editText_2,editText_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn = (Button)findViewById(R.id.button_gen);
        editText_1 = (EditText)findViewById(R.id.text_1);
        editText_2 = (EditText)findViewById(R.id.text_2);
        editText_3 = (EditText)findViewById(R.id.text_3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                intent.putExtra("Item1",editText_1.getText().toString());
                intent.putExtra("Item2",editText_2.getText().toString());
                intent.putExtra("Item3",editText_3.getText().toString());

                startActivity(intent);
//                finish();
            }
        });
    }
}