package com.v2.sample.babelfish.feature.unit.business

import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.BeforeEach

import org.mockito.Mockito.spy
import java.lang.RuntimeException

class UnitGETBulbasaurTest {
    private val param = null
    private lateinit var useCase: UnitGET
    private lateinit var repo: UnitRepository

    @BeforeEach
    fun setup() {
        repo = mock()
        useCase = spy(UnitGET(repo))
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