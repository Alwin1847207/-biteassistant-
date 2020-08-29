package com.example.biteassistant;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recipeAdapter extends RecyclerView.Adapter<recipeAdapter.MyViewHolder> {
    private ArrayList<recipeModel> modellist;
    private Context ctx;
    SparseBooleanArray selectedItem;
    public static int currentSelectedIndex;

    public recipeAdapter(Context ctx,ArrayList<recipeModel> modellist){
        this.ctx = ctx;
        this.modellist = modellist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView item_name;
        TextView ingredients_list;
        TextView steps;

        public MyViewHolder(View view){
            super(view);

            item_name = (TextView)view.findViewById(R.id.itemname);
            ingredients_list = (TextView)view.findViewById(R.id.ingredients);
            steps = (TextView)view.findViewById(R.id.steps);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            Toast.makeText(ctx, this.item_name.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){
        recipeModel model = modellist.get(position);
        holder.item_name.setText(model.getItemname());
        holder.ingredients_list.setText(model.getIngredients());
        holder.steps.setText(model.getSteps());
    }

    @Override
    public int getItemCount(){
        return  modellist.size();
    }
}
