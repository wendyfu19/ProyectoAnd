package com.star.fuertes.proyecto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.star.fuertes.proyecto.adapters.ListaPlanetasAdapter;
import com.star.fuertes.proyecto.model.Planet;
import com.star.fuertes.proyecto.model.PlanetRespuesta;
import com.star.fuertes.proyecto.service.PlanetService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fuertes on 19/09/2017.
 */

public class PlanetActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG = "STAR WARS PELICULAS";
    private RecyclerView recyclerView;
    private ListaPlanetasAdapter listaPerAdapter;

    private boolean aptoParaCargar;
    private int offset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaPerAdapter = new ListaPlanetasAdapter(this);
        recyclerView.setAdapter(listaPerAdapter);
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
        PlanetService service = retrofit.create(PlanetService.class);
        Call<PlanetRespuesta> personaRespuestaCall = service.obtenerListaFilms(10, offset);
        personaRespuestaCall.enqueue(new Callback<PlanetRespuesta>() {
            @Override
            public void onResponse(Call<PlanetRespuesta> call, Response<PlanetRespuesta> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {

                    PlanetRespuesta perRespuesta = response.body();
                    ArrayList<Planet> listaFilms = perRespuesta.getResults();

                    listaPerAdapter.adicionarListaFilms(listaFilms);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PlanetRespuesta> call, Throwable t) {
                Log.e(TAG, "onfailure: " + t.getMessage());
            }
        });



    }
}
