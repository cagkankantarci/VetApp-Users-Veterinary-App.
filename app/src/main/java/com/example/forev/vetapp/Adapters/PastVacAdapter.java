package com.example.forev.vetapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.forev.vetapp.Models.VacModel;
import com.example.forev.vetapp.R;

import java.util.List;

public class PastVacAdapter extends RecyclerView.Adapter<PastVacAdapter.ViewHolder>{

    List<VacModel> list;
    Context context;

    public PastVacAdapter(List<VacModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.reportcard__pastvac_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.pastVacNameText.setText(list.get(position).getVacname().toString()+" vaccine was made.");
        viewHolder.pastVacInfoText.setText("A "+list.get(position).getVacname() +" vaccine was made to "+list.get(position).getPetname()
                +" on "+list.get(position).getVacdate()+".");

        //Picasso.get().load(list.get(position).getPetimage().toString()).into(viewHolder.pastVacCircleImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView pastVacNameText, pastVacInfoText;
        //CircleImageView pastVacCircleImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            pastVacNameText = (TextView)itemView.findViewById(R.id.pastVacNameText);
            pastVacInfoText = (TextView)itemView.findViewById(R.id.pastVacInfoText);
            //pastVacCircleImageView = (CircleImageView)itemView.findViewById(R.id.pastVacCircleImageView);
        }
    }
}
