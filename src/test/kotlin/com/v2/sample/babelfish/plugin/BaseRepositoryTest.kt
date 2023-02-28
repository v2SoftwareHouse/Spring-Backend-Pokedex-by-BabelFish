package com.v2.sample.babelfish.plugin

import com.v2.babelfish.business.exception.HttpException
import com.nhaarman.mockitokotlin2.spy
import com.v2.babelfish.business.exception.InternetConnectionException
import com.v2.sample.babelfish.plugin.api.BaseRepository
import com.v2.sample.babelfish.plugin.api.PokedexAPI
import com.v2.sample.babelfish.plugin.api.PokedexAPIBuilder
import okhttp3.Interceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.SocketPolicy
import org.junit.jupiter.api.BeforeEach

class BaseRepositoryTest : AbstractRepositoryTest<MockRepository>() {

    @BeforeEach
    override fun setup() {
        super.setup()
    }

    override fun setupRepository() {
        repository = spy(MockRepository(setupUrl()))
    }

    @org.junit.Test(expected = HttpException::class)
    fun `when any request returns 4XX, then throw HttpException`() {
        val response = MockResponse().apply { setResponseCode(400) }
        server.enqueue(response)
        repository.dumbRequest()
    }

    @org.junit.Test(expected = HttpException::class)
    fun `when any request returns 5XX, then throw HttpException`() {
        val response = MockResponse().apply { setResponseCode(500) }
        server.enqueue(response)
        repository.dumbRequest()
    }

    @org.junit.Test(expected = InternetConnectionException::class)
    fun `when any request is timed out, then throw InternetConnectionException`() {
        val response = MockResponse().apply { socketPolicy = SocketPolicy.NO_RESPONSE }
        server.enqueue(response)
        repository.dumbRequest()
    }
}

open class MockRepository(baseUrl: String) : BaseRepository(baseUrl) {
    override fun getService(interceptors: List<Interceptor>): PokedexAPI {
        return PokedexAPIBuilder(baseUrl, interceptors).build()
    }
}