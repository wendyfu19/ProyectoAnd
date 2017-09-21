package com.star.fuertes.proyecto.service;

import com.star.fuertes.proyecto.model.PlanetRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Fuertes on 19/09/2017.
 */

public interface PlanetService {
    @GET("planets")
    Call<PlanetRespuesta> obtenerListaFilms(@Query("limit") int limit, @Query("offset") int offset);

}
