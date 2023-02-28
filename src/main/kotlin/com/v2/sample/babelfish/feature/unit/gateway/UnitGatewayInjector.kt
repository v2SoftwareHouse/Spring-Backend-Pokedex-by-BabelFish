package com.v2.sample.babelfish.feature.unit.gateway


interface UnitGatewayInjector {
    companion object {
        lateinit var self: UnitGatewayInjector
    }

    val presenter: UnitPresenter
}