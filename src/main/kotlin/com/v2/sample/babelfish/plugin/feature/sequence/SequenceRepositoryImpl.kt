package com.v2.sample.babelfish.plugin.feature.sequence

import com.v2.sample.babelfish.feature.sequence.business.SequenceRepository
import com.v2.sample.babelfish.plugin.api.BaseRepository
import com.v2.sample.babelfish.plugin.api.PokedexAPI
import com.v2.sample.babelfish.plugin.api.PokedexAPIBuilder
import okhttp3.Interceptor

open class SequenceRepositoryImpl(url: String) :
    BaseRepository(url), SequenceRepository {
    override fun getBulbasaur(): Any? {
        return getBodyOrThrow(getService().fetch("Bulbasaur"))
    }

    override fun getIvysaur(): Any? {
        return getBodyOrThrow(getService().fetch("Ivysaur"))
    }
    override fun getVenusaur(): Any? {
        return getBodyOrThrow(getService().fetch("Venusaur"))
    }
    override fun getService(interceptors: List<Interceptor>): PokedexAPI {
        return PokedexAPIBuilder(baseUrl).build()
    }
}