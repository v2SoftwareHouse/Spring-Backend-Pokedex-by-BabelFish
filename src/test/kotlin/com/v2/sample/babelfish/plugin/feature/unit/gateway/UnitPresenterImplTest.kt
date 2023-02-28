package com.v2.sample.babelfish.plugin.feature.unit.gateway

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import com.v2.babelfish.controller.BaseControllerTest
import com.v2.sample.babelfish.SampleDataFactory
import com.v2.sample.babelfish.feature.unit.UnitController
import com.v2.sample.babelfish.feature.unit.business.UnitRepository
import com.v2.sample.babelfish.feature.unit.business.UnitGET
import com.v2.sample.babelfish.feature.unit.gateway.UnitGatewayInjector
import com.v2.sample.babelfish.feature.unit.gateway.UnitPresenter
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


class UnitPresenterImplTest : BaseControllerTest<UnitController>() {
    private lateinit var repository: UnitRepository

    @BeforeEach
    override fun setup() {
        super.setup()
    }

    @AfterEach
    override fun teardown() {
        super.teardown()
    }

    override fun setupController() {
        repository = mock()
        UnitGatewayInjector.self = object : UnitGatewayInjector {
            override val presenter: UnitPresenter
                get() = UnitPresenterImpl(UnitGET(repository))
        }
        controller = spy(UnitController())
    }

    @org.junit.jupiter.api.Test
    fun `when fetch succeeds, assert callbacks`() {
        val result = arrangeFetch()
        doGet()
        assertViewStateSuccess(result)
    }

    @org.junit.jupiter.api.Test
    fun `when fetch throws exception, assert callbacks`() {
        val exception = RuntimeException()
        whenever(repository.doFetch(DEFAULT_NAME)).thenThrow(exception)
        doGet()
        assertViewStateError(exception)
    }

    private fun doGet() {
        controller?.greeting(SampleDataFactory.randomUuid())
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