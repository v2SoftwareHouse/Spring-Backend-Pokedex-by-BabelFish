package com.v2.sample.babelfish.plugin.feature.sequence.gateway

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.whenever
import com.v2.babelfish.controller.BaseControllerTest
import com.v2.sample.babelfish.feature.sequence.SequenceController
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETBulbasaur
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETIvysaur
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETVenusaur
import com.v2.sample.babelfish.feature.sequence.business.SequenceRepository
import com.v2.sample.babelfish.feature.sequence.gateway.SequenceGatewayInjector
import com.v2.sample.babelfish.feature.sequence.gateway.SequencePresenter
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


class SequencePresenterImplTest : BaseControllerTest<SequenceController>() {
    private lateinit var repository: SequenceRepository

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
        SequenceGatewayInjector.self = object : SequenceGatewayInjector {
            override val presenter: SequencePresenter
                get() = SequencePresenterImpl(
                    SequenceGETBulbasaur(repository),
                    SequenceGETIvysaur(repository),
                    SequenceGETVenusaur(repository)
                )
        }
        controller = spy(SequenceController())
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
        whenever(repository.getBulbasaur()).thenThrow(exception)
        whenever(repository.getIvysaur()).thenThrow(exception)
        whenever(repository.getVenusaur()).thenThrow(exception)
        doGet()
        assertViewStateError(exception)
    }

    private fun doGet() {
        controller?.greeting()
    }

    private fun arrangeFetch(): Any {
        val result = mock<Any>()
        whenever(repository.getBulbasaur()).thenReturn(result)
        whenever(repository.getBulbasaur()).thenReturn(result)
        whenever(repository.getBulbasaur()).thenReturn(result)

        return result
    }
}