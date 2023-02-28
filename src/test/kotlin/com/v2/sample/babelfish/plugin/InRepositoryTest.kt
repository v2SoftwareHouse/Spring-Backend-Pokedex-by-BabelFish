package com.v2.sample.babelfish.plugin

import com.v2.sample.babelfish.plugin.api.BaseRepository
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


abstract class InRepositoryTest<T : BaseRepository> : AbstractRepositoryTest<T>() {
    protected val authToken = "token"

    @BeforeEach
    override fun setup() {
        super.setup()
    }

    final override fun setupRepository() {
        val httpUrl = setupUrl()
        setupRepository(httpUrl)
    }

    @AfterEach
    fun teardown() {
        server.shutdown()
    }

    @org.junit.jupiter.api.Test
    fun `when auth is required, then authorization header is set`() {
        enqueueEmptyResponse(200)

        repository.dumbRequest()

        val request = server.takeRequest()
        val header = request.getHeader("Authorization")
        Assert.assertEquals(header, null)
    }

    abstract fun setupRepository(httpUrl: String)

    protected fun enqueueEmptyResponse(code: Int) {
        val response = MockResponse().apply {
            setResponseCode(code)
            setBody("{}")
        }

        server.enqueue(response)
    }

    protected fun enqueueResponse(code: Int, body: String) {
        val response = MockResponse().apply {
            setResponseCode(code)
            setBody(body)
        }

        server.enqueue(response)
    }
}