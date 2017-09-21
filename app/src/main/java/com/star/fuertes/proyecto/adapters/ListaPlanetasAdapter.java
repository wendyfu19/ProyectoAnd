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
import com.star.fuertes.proyecto.model.Planet;

import java.util.ArrayList;

/**
 * Created by Fuertes on 19/09/2017.
 */

public class ListaPlanetasAdapter extends RecyclerView.Adapter<ListaPlanetasAdapter.ViewHolder>  {

    private ArrayList<Planet> dataset;
    private Context context;

    public ListaPlanetasAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Planet p = dataset.get(position);
        holder.nameTextView.setText(p.getName());
      holder.potextView.setText(p.getPopulation());

        Glide.with(context)
                .load("http://www.vinilostickers.com/media/catalog/product/cache/5/color_selector1/430x323/9df78eab33525d08d6e5fb8d27136e95/1/0/100150-vinilo-infantil-decorativo-logo-planeta.png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.LogoView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaFilms(ArrayList<Planet> listaPersona) {
        dataset.addAll(listaPersona);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView nameTextView;
        private TextView potextView;
        private ImageView LogoView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            potextView = (TextView) itemView.findViewById(R.id.potextView);
            LogoView = (ImageView) itemView.findViewById(R.id.LogoView);

        }
    }
}
