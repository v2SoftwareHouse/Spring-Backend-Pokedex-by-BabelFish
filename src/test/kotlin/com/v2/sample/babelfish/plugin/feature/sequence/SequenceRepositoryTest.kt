package com.v2.sample.babelfish.plugin.feature.sequence

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.spy
import com.v2.babelfish.business.exception.HttpException
import com.v2.sample.babelfish.SampleDataFactory
import com.v2.sample.babelfish.plugin.InRepositoryTest
import org.junit.Assert

class SequenceRepositoryTest : InRepositoryTest<SequenceRepositoryImpl>() {

    override fun setupRepository(httpUrl: String) {
        repository = spy(SequenceRepositoryImpl(httpUrl))
    }

    @org.junit.jupiter.api.Test
    fun `when getBulbasaur succeeds, the return a Result`() {
        val value = SampleDataFactory.mockPokemon()
        doReturn(value).`when`(repository).getBulbasaur()

        val result = repository.getBulbasaur()
        Assert.assertEquals(value, result)
    }

    @org.junit.jupiter.api.Test
    fun `when getIvysaur succeeds, the return a Result`() {
        val value = SampleDataFactory.mockPokemon()
        doReturn(value).`when`(repository).getIvysaur()

        val result = repository.getBulbasaur()
        Assert.assertEquals(value, result)
    }

    @org.junit.jupiter.api.Test
    fun `when getVenusaur succeeds, the return a Result`() {
        val value = SampleDataFactory.mockPokemon()
        doReturn(value).`when`(repository).getVenusaur()

        val result = repository.getBulbasaur()
        Assert.assertEquals(value, result)
    }

    @org.junit.Test(expected = HttpException::class)
    fun `when fetch request returns 5XX, then throw HttpException `() {
        enqueueEmptyResponse(500)

        repository.getBulbasaur()
        val request = server.takeRequest()
        Assert.assertNotNull(request.body)
    }

    @org.junit.Test(expected = HttpException::class)
    fun `when getIvysaur request returns 5XX, then throw HttpException `() {
        enqueueEmptyResponse(500)

        repository.getIvysaur()
        val request = server.takeRequest()
        Assert.assertNotNull(request.body)
    }

    @org.junit.Test(expected = HttpException::class)
    fun `when getVenusaur request returns 5XX, then throw HttpException `() {
        enqueueEmptyResponse(500)

        repository.getVenusaur()
        val request = server.takeRequest()
        Assert.assertNotNull(request.body)
    }
}