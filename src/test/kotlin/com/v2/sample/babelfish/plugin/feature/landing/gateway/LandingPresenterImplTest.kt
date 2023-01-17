package com.v2.sample.babelfish.plugin.feature.landing.gateway

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import com.v2.babelfish.controller.BaseControllerTest
import com.v2.sample.babelfish.DataFactory
import com.v2.sample.babelfish.feature.landing.LandingController
import com.v2.sample.babelfish.feature.landing.business.LandingRepository
import com.v2.sample.babelfish.feature.landing.business.LandingUseCase
import com.v2.sample.babelfish.feature.landing.gateway.LandingGatewayInjector
import com.v2.sample.babelfish.feature.landing.gateway.LandingPresenter

import org.junit.After
import org.junit.Before
import org.junit.Test

class LandingPresenterImplTest : BaseControllerTest<LandingController>() {
    private lateinit var repository: LandingRepository

    @Before
    override fun setup() {
        super.setup()
    }

    @After
    override fun teardown() {
        super.teardown()
    }

    override fun setupController() {
        repository = mock()
        LandingGatewayInjector.self = object : LandingGatewayInjector {
            override val presenter: LandingPresenter
                get() = LandingPresenterImpl(LandingUseCase(repository))
        }
        controller = spy(LandingController())
    }

    @Test
    fun `when fetch succeeds, assert callbacks`() {
        val result = arrangeFetch()
        doGet()
        assertViewStateSuccess(result)
    }

    @Test
    fun `when fetch throws exception, assert callbacks`() {
        val exception = RuntimeException()
        whenever(repository.doFetch(DEFAULT_NAME)).thenThrow(exception)
        doGet()
        assertViewStateError(exception)
    }

    private fun doGet() {
        controller?.greeting(DataFactory.randomUuid())
    }

    private fun arrangeFetch(): Any {
        val result = mock<Any>()
        whenever(repository.doFetch(DEFAULT_NAME)).thenReturn(result)

        return result
    }

    companion object{
        private const val DEFAULT_NAME = "bulbasaur"
    }
}