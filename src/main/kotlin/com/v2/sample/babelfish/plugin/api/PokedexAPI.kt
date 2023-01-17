package com.v2.sample.babelfish.plugin.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexAPI {
    @GET("pokemon/")
    fun fetch(@Query("name") name: String?):Call<Any>

    @GET("test")
    fun dumbRequest(): Call<Any?>
}