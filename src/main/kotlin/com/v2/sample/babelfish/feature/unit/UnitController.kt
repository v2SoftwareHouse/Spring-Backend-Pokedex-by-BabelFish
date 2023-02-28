package com.v2.sample.babelfish.feature.unit

import com.v2.sample.babelfish.feature.unit.gateway.UnitGatewayInjector
import com.v2.sample.babelfish.feature.unit.gateway.UnitPresenter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
open class UnitController {
    private val presenter by lazy { injectPresenter() }
    private fun injectPresenter(): UnitPresenter {
        return UnitGatewayInjector.self.presenter
    }

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = DEFAULT) name: String): Any? {
        return presenter.doFetch(name)
    }

    companion object {
        private const val DEFAULT = "bulbasaur"
    }
}