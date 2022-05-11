package com.diplomado.laboratorio2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getRestaurantes(@Url url:String): Response<RestauranteResponse>

    @GET
    suspend fun userIsValid(@Url url:String): Response<UserIsValidResponse>
}