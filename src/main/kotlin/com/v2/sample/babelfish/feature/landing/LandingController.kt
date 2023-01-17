package com.v2.sample.babelfish.feature.landing

import com.v2.sample.babelfish.feature.landing.gateway.LandingGatewayInjector
import com.v2.sample.babelfish.feature.landing.gateway.LandingPresenter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
open class LandingController {
    private val presenter by lazy { injectPresenter() }
    private fun injectPresenter(): LandingPresenter {
        return LandingGatewayInjector.self.presenter
    }

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = DEFAULT) name: String): Any? {
        return presenter.doFetch(name)
    }

    companion object {
        private const val DEFAULT = "bulbasaur"
    }
}