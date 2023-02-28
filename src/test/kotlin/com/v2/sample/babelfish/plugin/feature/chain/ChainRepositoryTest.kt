package com.v2.sample.babelfish.plugin.feature.chain

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.spy
import com.v2.babelfish.business.exception.HttpException
import com.v2.sample.babelfish.SampleDataFactory
import com.v2.sample.babelfish.plugin.InRepositoryTest
import org.junit.Assert



class ChainRepositoryTest : InRepositoryTest<ChainRepositoryImpl>() {

    override fun setupRepository(httpUrl: String) {
        repository = spy(ChainRepositoryImpl(httpUrl))
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

        val result = repository.getIvysaur()
        Assert.assertEquals(value, result)
    }

    @org.junit.Test(expected = HttpException::class)
    fun `when getBulbasaur request returns 5XX, then throw HttpException `() {
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
}