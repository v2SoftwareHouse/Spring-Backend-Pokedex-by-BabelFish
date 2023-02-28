package com.v2.sample.babelfish.plugin.feature.chain.gateway

import com.v2.babelfish.business.interactor.ChainedUseCase
import com.v2.babelfish.controller.BaseController
import com.v2.sample.babelfish.feature.chain.business.ChainGETBulbasaur
import com.v2.sample.babelfish.feature.chain.business.ChainGETIvysaur
import com.v2.sample.babelfish.feature.chain.gateway.ChainPresenter

class ChainPresenterImpl(
    private val getBulbasaur: ChainGETBulbasaur,
    private val getIvysaur: ChainGETIvysaur
) : BaseController(), ChainPresenter {
    override fun doFetch(): Any? {
        val chainedUseCase = ChainedUseCase(getBulbasaur, getIvysaur)
        return processUseCase(null, chainedUseCase)?.value
    }
}