package com.star.fuertes.proyecto.service;

import com.star.fuertes.proyecto.model.PlanetRespuesta;
import com.star.fuertes.proyecto.model.SpeciesRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ALE on 20/09/2017.
 */

public interface SpeciesService {
    @GET("species")
    Call<SpeciesRespuesta> obtenerListaSpecies(@Query("limit") int limit, @Query("offset") int offset);
}
