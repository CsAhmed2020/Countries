package com.example.countries.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.countries.R;
import com.example.countries.model.Countries;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private ArrayList<Countries> listCountries ;
    private final LayoutInflater inflater;
    private final OnNoteList onNoteList;
    private final Activity activity;
    private Context context;

    public CountryAdapter(Context ctx, Activity act, ArrayList<Countries> dataModelArrayList, OnNoteList onNoteList){
        inflater = LayoutInflater.from(ctx);
        this.listCountries = dataModelArrayList;
        this.onNoteList = onNoteList;
        this.activity = act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false),onNoteList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SvgLoader.pluck()
                .with(activity)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(listCountries.get(position).getFlag(), holder.iv_flag);

        holder.name.setText(listCountries.get(position).getName());
        holder.capital.setText("Capital : "+listCountries.get(position).getCapital());
        holder.region.setText("Region : " + listCountries.get(position).getRegion());

    }

    public void setCountries(ArrayList<Countries> listCountries) {
        this.listCountries = listCountries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, capital, region;
        ImageView iv_flag;
        OnNoteList onNoteList;

        public ViewHolder(@NonNull View itemView,OnNoteList onNoteList) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            capital = itemView.findViewById(R.id.tv_capital);
            region = itemView.findViewById(R.id.tv_region);
            iv_flag = itemView.findViewById(R.id.iv_flag);
            this.onNoteList = onNoteList;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteList.OnnoteClick(listCountries.get(getAdapterPosition()));

        }
    }

    public interface OnNoteList {
        void OnnoteClick(Countries userClass);


    }
    public void filteredlist(ArrayList<Countries> filterlist){
        listCountries = filterlist;
        notifyDataSetChanged();
    }
    public void updateList(ArrayList<Countries> updateList){
        listCountries = updateList;
        notifyDataSetChanged();
    }
}
