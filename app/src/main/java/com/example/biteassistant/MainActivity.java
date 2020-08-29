package com.example.biteassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("recipes");

        modellist = new ArrayList<recipeModel>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modellist.clear();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String k=dataSnapshot1.getKey();
                    String itemname = dataSnapshot1.child("r_name").getValue(String.class);
                    String ingredients = "Ingredients : ";
                    String steps = "Steps : ";

                    for(int i = 0 ; i < dataSnapshot1.child("ingredient").getChildrenCount(); i++){
                        ingredients = ingredients + " " + dataSnapshot1.child("ingredient").child(String.valueOf(i)).getValue(String.class);
                    }

                    for(int i = 0 ; i < dataSnapshot1.child("pre_step").getChildrenCount(); i++){
                        steps = steps + " " + dataSnapshot1.child("pre_step").child(String.valueOf(i)).getValue(String.class);
                    }

                    recipeModel modl = new recipeModel(itemname,ingredients,steps);
                    modellist.add(modl);
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