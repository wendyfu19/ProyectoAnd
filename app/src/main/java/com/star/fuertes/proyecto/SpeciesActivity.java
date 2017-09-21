package com.star.fuertes.proyecto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.star.fuertes.proyecto.adapters.ListaSpeciesAdapter;

import com.star.fuertes.proyecto.model.Species;
import com.star.fuertes.proyecto.model.SpeciesRespuesta;
import com.star.fuertes.proyecto.service.SpeciesService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fuertes on 20/09/2017.
 */

public class SpeciesActivity  extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG = "STAR WARS ESPECIES";
    private RecyclerView recyclerView;
    private ListaSpeciesAdapter listaSpeAdapter;

    private boolean aptoParaCargar;
    private int offset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaSpeAdapter = new ListaSpeciesAdapter(this);
        recyclerView.setAdapter(listaSpeAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 10;
                            ObtenerDatos(offset);
                        }
                    }
                }
            }
        });


        retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        offset = 0;
        ObtenerDatos(offset);

    }
    private void ObtenerDatos(int offset) {
        SpeciesService service = retrofit.create(SpeciesService.class);
        Call<SpeciesRespuesta> speciesRespuestaCall = service.obtenerListaSpecies(10,offset);
        speciesRespuestaCall.enqueue(new Callback<SpeciesRespuesta>() {
            @Override
            public void onResponse(Call<SpeciesRespuesta> call, Response<SpeciesRespuesta> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {

                    SpeciesRespuesta speRespuesta = response.body();
                    ArrayList<Species> listaFilms = speRespuesta.getResults();

                    listaSpeAdapter.adicionarListaFilms(listaFilms);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<SpeciesRespuesta> call, Throwable t) {
                Log.e(TAG, "onfailure: " + t.getMessage());
            }
        });



    }
}
