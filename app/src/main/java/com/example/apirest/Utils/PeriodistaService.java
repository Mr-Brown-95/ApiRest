package com.example.apirest.Utils;

import com.example.apirest.Model.Periodista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PeriodistaService {

    @GET("journalistsServidor")
    Call<List<Periodista>> getPeriodista();

    @POST("journalistsServidor")
    Call<Periodista>addPeriodista(@Body Periodista periodista);

    @PUT("journalistsServidor/{id}")
    Call<Periodista>updatePeriodista(@Body Periodista periodista, @Path("id") int id);

    @DELETE("journalistsServidor/{id}")
    Call<Periodista>deletePeriodista(@Path("id")int id);

}
