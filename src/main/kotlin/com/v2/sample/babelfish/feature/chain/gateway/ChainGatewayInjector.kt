package com.v2.sample.babelfish.feature.chain.gateway


interface ChainGatewayInjector {
    companion object {
        lateinit var self: ChainGatewayInjector
    }

    val presenter: ChainPresenter
}