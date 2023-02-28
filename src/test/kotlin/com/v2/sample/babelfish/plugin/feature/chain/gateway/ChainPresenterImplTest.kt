package com.v2.sample.babelfish.plugin.feature.chain.gateway

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import com.v2.babelfish.controller.BaseControllerTest
import com.v2.sample.babelfish.feature.chain.ChainController
import com.v2.sample.babelfish.feature.chain.business.ChainGETBulbasaur
import com.v2.sample.babelfish.feature.chain.business.ChainGETIvysaur
import com.v2.sample.babelfish.feature.chain.business.ChainRepository
import com.v2.sample.babelfish.feature.chain.gateway.ChainGatewayInjector
import com.v2.sample.babelfish.feature.chain.gateway.ChainPresenter
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


class ChainPresenterImplTest : BaseControllerTest<ChainController>() {
    private lateinit var repository: ChainRepository

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
        ChainGatewayInjector.self = object : ChainGatewayInjector {
            override val presenter: ChainPresenter
                get() = ChainPresenterImpl(
                    ChainGETBulbasaur(repository),
                    ChainGETIvysaur(repository)
                )
        }
        controller = spy(ChainController())
    }

    @org.junit.jupiter.api.Test
    fun `when fetch succeeds, assert callbacks`() {
        val result = arrangeGet()
        doGet()
        assertViewStateSuccess(result)
    }

    @org.junit.jupiter.api.Test
    fun `when fetch throws exception, assert callbacks`() {
        val exception = RuntimeException()
        whenever(repository.getBulbasaur()).thenThrow(exception)
        whenever(repository.getIvysaur()).thenThrow(exception)
        doGet()
        assertViewStateError(exception)
    }

    private fun doGet() {
        controller?.greeting()
    }

    private fun arrangeGet(): Any {
        val result = mock<Any>()
        whenever(repository.getBulbasaur()).thenReturn(result)
        whenever(repository.getIvysaur()).thenReturn(result)

        return result
    }
}