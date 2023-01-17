package com.v2.sample.babelfish.plugin.feature.landing

import com.v2.sample.babelfish.plugin.api.BaseRepository
import com.v2.sample.babelfish.plugin.api.PokedexAPI
import com.v2.sample.babelfish.plugin.api.PokedexAPIBuilder
import okhttp3.Interceptor
import com.v2.sample.babelfish.feature.landing.business.LandingRepository

open class LandingRepositoryImpl(url: String) :
        BaseRepository(url), LandingRepository {

    override fun doFetch(name:String?): Any? {
        return getBodyOrThrow(getService().fetch(name))
    }

    override fun getService(interceptors: List<Interceptor>): PokedexAPI {
        return PokedexAPIBuilder(baseUrl).build()
    }
}