package com.v2.sample.babelfish.feature.chain

import com.v2.sample.babelfish.feature.chain.gateway.ChainGatewayInjector
import com.v2.sample.babelfish.feature.chain.gateway.ChainPresenter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
open class ChainController {
    private val presenter by lazy { injectPresenter() }
    private fun injectPresenter(): ChainPresenter {
        return ChainGatewayInjector.self.presenter
    }

    @GetMapping("/chain/greeting")
    fun greeting(): Any? {
        return presenter.doFetch()
    }
}