package com.v2.sample.babelfish.plugin.feature.sequence.gateway


import com.v2.babelfish.business.interactor.SequenceUseCase
import com.v2.babelfish.controller.BaseController
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETBulbasaur
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETIvysaur
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETVenusaur
import com.v2.sample.babelfish.feature.sequence.gateway.SequencePresenter


class SequencePresenterImpl(
    private val getBulbasaur: SequenceGETBulbasaur,
    private val getIvysaur: SequenceGETIvysaur,
    private val getVenusaur: SequenceGETVenusaur
) : BaseController(), SequencePresenter {
    override fun doFetch(): Any? {
        val sequence = SequenceUseCase.builder()
            .add(getBulbasaur)
            .add(getIvysaur)
            .add(getVenusaur)
            .build()

        return processUseCase(null, sequence)?.value
    }
}


