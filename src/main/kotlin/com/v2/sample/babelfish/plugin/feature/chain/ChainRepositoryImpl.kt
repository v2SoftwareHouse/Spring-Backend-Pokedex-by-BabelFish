package com.v2.sample.babelfish.plugin.feature.chain

import com.v2.sample.babelfish.feature.chain.business.ChainRepository
import com.v2.sample.babelfish.plugin.api.BaseRepository
import com.v2.sample.babelfish.plugin.api.PokedexAPI
import com.v2.sample.babelfish.plugin.api.PokedexAPIBuilder
import okhttp3.Interceptor
open class ChainRepositoryImpl(url: String)
    : BaseRepository(url), ChainRepository {

    override fun getBulbasaur(): Any? {
        return getBodyOrThrow(getService().fetchBulbasaur())
    }

    override fun getIvysaur(): Any? {
        return getBodyOrThrow(getService().fetchIvysaur())
    }

    override fun getService(interceptors: List<Interceptor>): PokedexAPI {
        return PokedexAPIBuilder(baseUrl).build()
    }
}