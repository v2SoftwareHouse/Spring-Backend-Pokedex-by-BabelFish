package com.v2.sample.babelfish.plugin.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexAPI {
    @GET("pokemon/")
    fun fetch(@Query("name") name: String?):Call<Any>

    @GET("pokemon/bulbasaur")
    fun fetchBulbasaur(): Call<Any>

    @GET("pokemon/ivysaur")
    fun fetchIvysaur(): Call<Any>

    @GET("pokemon/venusaur")
    fun fetchVenusaur(): Call<Any>

    @GET("test")
    fun dumbRequest(): Call<Any?>
}