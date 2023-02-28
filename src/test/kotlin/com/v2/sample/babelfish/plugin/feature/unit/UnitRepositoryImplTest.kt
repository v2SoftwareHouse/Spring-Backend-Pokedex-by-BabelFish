package com.v2.sample.babelfish.plugin.feature.unit

import com.nhaarman.mockitokotlin2.*
import com.v2.babelfish.business.exception.HttpException
import com.v2.sample.babelfish.SampleDataFactory
import com.v2.sample.babelfish.plugin.InRepositoryTest
import org.junit.Assert


class UnitRepositoryImplTest : InRepositoryTest<UnitRepositoryImpl>() {

    override fun setupRepository(httpUrl: String) {
        repository = spy(UnitRepositoryImpl(httpUrl))
    }

    @org.junit.jupiter.api.Test
    fun `when fetch succeeds, the return a Result`() {
        val value = SampleDataFactory.mockPokemon()
        doReturn(value).`when`(repository).doFetch(DEFAULT_NAME)

        val result = repository.doFetch(DEFAULT_NAME)
        Assert.assertEquals(value, result)
    }

    @org.junit.Test(expected = HttpException::class)
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