package com.v2.sample.babelfish.plugin.api

import com.v2.babelfish.business.exception.AuthenticationException
import com.v2.babelfish.business.exception.HttpException
import com.v2.babelfish.business.exception.InternetConnectionException
import okhttp3.Interceptor
import retrofit2.Call
import java.io.IOException

abstract class BaseRepository(protected val baseUrl: String) {
    protected fun <R> getBodyOrThrow(call: Call<R>): R? {
        try {
            val response = call.execute()
            if (response.isSuccessful) return response.body()
            if (response.code() == 401) {
                throw AuthenticationException()
            }
            throw HttpException(response.code(), response.message())
        } catch (e: IOException) {
            e.printStackTrace()
            throw InternetConnectionException()
        }
    }

    abstract fun getService(interceptors: List<Interceptor> = emptyList()): PokedexAPI

    internal fun dumbRequest() {
        getBodyOrThrow(getService().dumbRequest())
    }
}