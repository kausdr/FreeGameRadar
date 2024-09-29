package com.example.recyclerviewapp.api

import com.example.recyclerviewapp.model.Filme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class MovieResponse(
    val results: List<Filme>
)

interface API {
    @GET("movie/popular")

    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): Call<MovieResponse>
}
