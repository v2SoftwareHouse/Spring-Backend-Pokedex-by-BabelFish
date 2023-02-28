package com.v2.sample.babelfish.feature.sequence.gateway


interface SequenceGatewayInjector {
    companion object {
        lateinit var self: SequenceGatewayInjector
    }

    val presenter: SequencePresenter
}