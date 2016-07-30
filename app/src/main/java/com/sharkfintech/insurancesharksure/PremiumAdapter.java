package com.sharkfintech.insurancesharksure;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Albert Zhang on 30/7/2016.
 */
public class PremiumAdapter extends RecyclerView.Adapter<PremiumAdapter.PremiumViewHolder>{
    List<Premium> premiums;
    public PremiumAdapter(List<Premium> premiums){
        this.premiums = premiums;
    }
    public static class PremiumViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView nameField;
        TextView descriptionField;

        public PremiumViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            nameField = (TextView) itemView.findViewById(R.id.card_name);
            descriptionField = (TextView) itemView.findViewById(R.id.card_description);
        }

    }
    @Override
    public int getItemCount(){
        return premiums.size();
    }
    @Override
    public PremiumViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PremiumViewHolder holder = new PremiumViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(PremiumViewHolder holder, int i){
        holder.nameField.setText(premiums.get(i).getName());
        holder.descriptionField.setText(premiums.get(i).getDescription());
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
