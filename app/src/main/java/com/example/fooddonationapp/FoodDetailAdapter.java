package com.example.fooddonationapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodDetailAdapter  extends RecyclerView.Adapter<FoodDetailAdapter.ViewHolder> {


    // creating variables for our ArrayList and context
    private ArrayList<FoodDetails> foodArrayList;
    private Context context;

    // creating constructor for our adapter class
    public FoodDetailAdapter(ArrayList<FoodDetails> foodArrayList, Context context) {
        this.foodArrayList = foodArrayList;
        this.context = context;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView foodName;
        private final TextView foodDuration;
        private final TextView foodPacket;
        private  final  TextView fooddate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            foodName= itemView.findViewById(R.id.idfooditem);
            foodDuration = itemView.findViewById(R.id.idtimeDuration);
            foodPacket = itemView.findViewById(R.id.Food_Packet);
            fooddate=itemView.findViewById(R.id.Food_date);
        }
    }
    @NonNull
    @Override
    public FoodDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.foodrequestdetails, parent, false));
    }@Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        FoodDetails foods = foodArrayList.get(position);
        holder.foodName.setText(foods.getFoodItem());
        holder.fooddate.setText(foods.getDatelimit());

        holder.foodPacket.setText(foods.getNoPackets()+" Packets");
        holder.foodDuration.setText(foods.getTimeDuration());

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return foodArrayList.size();
    }


}
