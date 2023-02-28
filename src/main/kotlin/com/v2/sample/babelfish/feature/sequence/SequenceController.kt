package com.v2.sample.babelfish.feature.sequence

import com.v2.sample.babelfish.feature.sequence.gateway.SequenceGatewayInjector
import com.v2.sample.babelfish.feature.sequence.gateway.SequencePresenter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
open class SequenceController {
    private val presenter by lazy { injectPresenter() }
    private fun injectPresenter(): SequencePresenter {
        return SequenceGatewayInjector.self.presenter
    }

    @GetMapping("/sequence/greeting")
    fun greeting(): Any? {
        return presenter.doFetch()
    }
}