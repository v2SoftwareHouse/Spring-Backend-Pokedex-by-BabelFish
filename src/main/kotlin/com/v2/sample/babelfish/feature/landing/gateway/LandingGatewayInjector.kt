package com.v2.sample.babelfish.feature.landing.gateway


interface LandingGatewayInjector {
    companion object {
        lateinit var self: LandingGatewayInjector
    }

    val presenter: LandingPresenter
}