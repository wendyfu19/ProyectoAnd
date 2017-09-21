package com.star.fuertes.proyecto.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.star.fuertes.proyecto.R;
import com.star.fuertes.proyecto.model.Species;

import java.util.ArrayList;

/**
 * Created by ALE on 20/09/2017.
 */

public class ListaSpeciesAdapter extends RecyclerView.Adapter<ListaSpeciesAdapter.ViewHolder>   {
    private ArrayList<Species> dataset;
    private Context context;

    public ListaSpeciesAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }
    @Override
    public ListaSpeciesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_species, parent, false);
        return new ListaSpeciesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaSpeciesAdapter.ViewHolder holder, int position) {
        Species p = dataset.get(position);
        holder.nameTextView.setText(p.getName());
        holder.letextView.setText(p.getLanguage());

        Glide.with(context)
                .load("https://ugc.kn3.net/i/origin/http://3.bp.blogspot.com/_Rtg3KhslSEE/Shghhd53cnI/AAAAAAAAAAc/crhheej_hqc/S740/stickman_waving_hc.gif")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.LogoView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaFilms(ArrayList<Species> listaPersona) {
        dataset.addAll(listaPersona);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView nameTextView;
        private TextView letextView;
        private ImageView LogoView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            letextView = (TextView) itemView.findViewById(R.id.letextView);
            LogoView = (ImageView) itemView.findViewById(R.id.LogoView);

        }
    }
}
