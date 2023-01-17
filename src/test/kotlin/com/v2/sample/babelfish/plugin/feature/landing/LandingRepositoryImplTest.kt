package com.v2.sample.babelfish.plugin.feature.landing

import com.nhaarman.mockitokotlin2.*
import com.v2.babelfish.business.exception.HttpException
import com.v2.sample.babelfish.DataFactory
import com.v2.sample.babelfish.plugin.InRepositoryTest
import org.junit.Assert
import org.junit.Test

class LandingRepositoryImplTest : InRepositoryTest<LandingRepositoryImpl>() {

    override fun setupRepository(httpUrl: String) {
        repository = spy(LandingRepositoryImpl(httpUrl))
    }

    @Test
    fun `when fetch succeeds, the return a Result`() {
        val value = DataFactory.mockPokemon()
        doReturn(value).`when`(repository).doFetch(DEFAULT_NAME)

        val result = repository.doFetch(DEFAULT_NAME)
        Assert.assertEquals(value, result)
    }

    @Test(expected = HttpException::class)
    fun `when fetch request returns 5XX, then throw HttpException `() {
        enqueueEmptyResponse(500)

        repository.doFetch(DEFAULT_NAME)
        val request = server.takeRequest()
        Assert.assertNotNull(request.body)
    }

    companion object {
        private const val DEFAULT_NAME = "bulbasaur"
    }
}