package com.example.biteassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<recipeModel> modellist;
    private recipeAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Intent intent=getIntent();
        final String item1=intent.getStringExtra("Item1");
        final String item2=intent.getStringExtra("Item2");
        final String item3=intent.getStringExtra("Item3");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = (DatabaseReference) FirebaseDatabase.getInstance().getReference("recipes");

        modellist = new ArrayList<recipeModel>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modellist.clear();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                   // String k=dataSnapshot1.getKey();
                    String itemname="Recipes : ";
                    String ingredients = "";
                    String steps = "";


                    ArrayList arrayList = new ArrayList();

                    for(int i = 0 ; i < dataSnapshot1.child("ingredient").getChildrenCount(); i++) {
                       String ing = dataSnapshot1.child("ingredient").child(String.valueOf(i)).getValue(String.class);
                       arrayList.add(ing);
                    }

                    if (arrayList.contains(item1) || arrayList.contains(item2) && arrayList.contains(item3)) {
                        for(int k = 0 ; k < dataSnapshot1.child("ingredient").getChildrenCount(); k++) {
                            ingredients = ingredients  + dataSnapshot1.child("ingredient").child(String.valueOf(k)).getValue(String.class)+ ", ";
                        }
                        itemname = dataSnapshot1.child("r_name").getValue(String.class);
                        for(int j = 0 ; j < dataSnapshot1.child("procedure").getChildrenCount(); j++){
                            steps = steps + dataSnapshot1.child("procedure").child(String.valueOf(j)).getValue(String.class)+ "\n";
                        }
                        recipeModel modl = new recipeModel(itemname,ingredients,steps);
                        modellist.add(modl);
                    }

//                    for(int i = 0 ; i < dataSnapshot1.child("procedure").getChildrenCount(); i++){
//                        steps = steps + " " + dataSnapshot1.child("procedure").child(String.valueOf(i)).getValue(String.class);
//                    }


//                    recipeModel modl = new recipeModel(itemname,ingredients,steps);
//                    modellist.add(modl);
                }

                itemAdapter = new recipeAdapter(getApplicationContext(),modellist);
                recyclerView.setAdapter(itemAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error....!!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}