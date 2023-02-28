package com.v2.sample.babelfish.feature.sequence.business

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import java.lang.RuntimeException

class SequenceGETVenusaurTest {
    private var param = null
    private lateinit var useCase: SequenceGETVenusaur
    private lateinit var repo: SequenceRepository

    @BeforeEach
    fun setup() {
        repo = mock()
        useCase = Mockito.spy(SequenceGETVenusaur(repo))
    }

    @org.junit.jupiter.api.Test
    fun `when not allowed by guard, then call onError function`() {
        useCase.process(param)
        verify(useCase, times(0)).onResult(argThat { this.isError() })
    }

    @org.junit.jupiter.api.Test
    fun `when allowed by guard, then call onSuccess function`() {
        useCase.process(param)
        verify(useCase, times(1)).onResult(argThat { this.isSuccess() })
    }

    @org.junit.jupiter.api.Test
    fun `when denied by guard, then call onGuardError function`() {
        val exception = RuntimeException()
        whenever(useCase.guard(eq(param))).thenThrow(exception)

        useCase.process(param)

        verify(useCase, times(1)).onResult(argThat { this.isError() })
    }

    @org.junit.Test
    fun `when guard throws error, then call onError function`() {
        val exception = RuntimeException()
        whenever(useCase.guard(eq(param))).thenThrow(exception)

        useCase.process(param)

        verify(useCase, times(1)).onError(eq(exception))
        verify(useCase, times(0)).onResult(argThat { this.isSuccess() })
    }

    @org.junit.Test
    fun `when execute throws error, then call onError function`() {
        val exception = RuntimeException()
        whenever(useCase.execute(eq(param))).thenThrow(exception)

        useCase.process(param)

        verify(useCase, times(1)).onError(eq(exception))
        verify(useCase, times(0)).onResult(argThat { this.isSuccess() })
    }
}