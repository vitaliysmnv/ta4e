package com.example.textadventuregame.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.textadventuregame.R;
import com.example.textadventuregame.model.items.Item;
import com.example.textadventuregame.model.items.Shield;

import java.util.List;


public class InventoryRecyclerViewAdapter extends RecyclerView.Adapter<InventoryRecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    List<Item> items;
    Item sword;
    Shield shield;
    Context context;
    public InventoryRecyclerViewAdapter(Context context, List<Item> items, Item sword, Shield shield,
                                        RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.items = items;
        this.sword = sword;
        this.shield = shield;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void setSword(Item sword) {
        this.sword = sword;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InventoryRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.inventory_item, parent, false);
        return new InventoryRecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getName());
        holder.itemName.setTextSize(15);
        holder.itemBonus.setText(items.get(position).getBonusesDesc());
        holder.itemBonus.setTextSize(15);
        String imageFileName = items.get(position).getImageFileName();
        int imageResourceId = context.getResources().getIdentifier(imageFileName, "drawable", context.getPackageName());
        holder.itemImage.setImageResource(imageResourceId);

        holder.inUseImage.setImageResource(R.drawable.ticked_image);
        if(items.get(position).equals(sword) || items.get(position).equals(shield)) {
            holder.inUseImage.setVisibility(View.VISIBLE);
        } else holder.inUseImage.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemName;
        TextView itemBonus;
        ImageView inUseImage;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemBonus = itemView.findViewById(R.id.itemBonus);
            inUseImage = itemView.findViewById(R.id.inUseImage);

            itemView.setOnClickListener(v -> {
                if(recyclerViewInterface!=null){
                    int pos = getAdapterPosition();

                    if(pos!= RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }


            });
        }

    }

}
