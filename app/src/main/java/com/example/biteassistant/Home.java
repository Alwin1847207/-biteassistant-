package com.example.biteassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

public class Home extends Activity {
    TextView editText_1, editText_2, editText_3;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private ImageView imageView;
    private Button btn,btn_gen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_gen2 = (Button) findViewById(R.id.button_gen2);
        imageView = (ImageView) findViewById(R.id.imageView2);
        btn = (Button) findViewById(R.id.button_gen);
        editText_1 = (TextView) findViewById(R.id.text_1);
        editText_2 = (TextView) findViewById(R.id.text_2);
        editText_3 = (TextView) findViewById(R.id.text_3);

        editText_1.setVisibility(View.INVISIBLE);
        editText_2.setVisibility(View.INVISIBLE);
        editText_3.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);

        imageView.setImageResource(R.drawable.food);
        btn_gen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText_1.getVisibility() == View.INVISIBLE || editText_2.getVisibility() == View.INVISIBLE || editText_3.getVisibility() == View.INVISIBLE){
                    Toast.makeText(Home.this, "Please Capture Image and Proceed !", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Home.this, MainActivity.class);
                    intent.putExtra("Item1", editText_1.getText().toString());
                    intent.putExtra("Item2", editText_2.getText().toString());
                    intent.putExtra("Item3", editText_3.getText().toString());

                    startActivity(intent);
//                finish();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

            String[] items = {"Chicken", "Bun" , "Worcestershire sauce","Tomato","Bread","Cabbage","Mayonnaise","Oil","Egg","Pepper","Wheat flour","Cheese","Chilli flakes","Mushroom","Honey","Corn flour","Tomato sauce","Tettuce","Kuboos","Banana","Paneer","Milk","Pineapple","Apple","Orange","Grapes","Ice cream","Onion","Fish","Noodles","Cucumber","Hotdog bun","Avocado","Watermelon","Green pepper","Bluebery","Pomegrante"};

            Random r=new Random();
            int randomNumber=r.nextInt(items.length);
            editText_1.setText(items[randomNumber]);

            r=new Random();
            randomNumber=r.nextInt(items.length);
            editText_2.setText(items[randomNumber]);

            r=new Random();
            randomNumber=r.nextInt(items.length);
            editText_3.setText(items[randomNumber]);

            editText_1.setVisibility(View.VISIBLE);
            editText_2.setVisibility(View.VISIBLE);
            editText_3.setVisibility(View.VISIBLE);
            btn.setVisibility(View.VISIBLE);

        }
    }
}