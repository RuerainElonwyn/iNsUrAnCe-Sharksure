package com.sharkfintech.insurancesharksure;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListOfPremiumsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListOfPremiumsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListOfPremiumsFragment extends Fragment {
    public ListOfPremiumsFragment() {
        // Required empty public constructor
    }

    public static ListOfPremiumsFragment newInstance(String param1, String param2) {
        ListOfPremiumsFragment fragment = new ListOfPremiumsFragment();
        return fragment;
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_of_premiums, container);
       CardView cardView = (CardView)view.findViewById(R.id.card_view);

        return view;
    }

}
class PremiumAdaptor extends RecyclerView.Adapter<PremiumAdaptor.PremiumViewHolder>{
    List<Premium> premiums;
    public PremiumAdaptor(List<Premium> premiums){
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
