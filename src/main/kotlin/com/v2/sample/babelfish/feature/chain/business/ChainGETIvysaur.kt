package com.v2.sample.babelfish.feature.chain.business

import com.v2.babelfish.business.dto.Output
import com.v2.babelfish.business.dto.ValueOutput
import com.v2.babelfish.business.interactor.UseCase

open class ChainGETIvysaur(
        private val repo: ChainRepository
) : UseCase<MutableList<Any?>, Any>() {
    override fun execute(param: MutableList<Any?>?): Output<Any> {
        val ivysaur = repo.getIvysaur()
        param?.add(ivysaur)

        return ValueOutput(param)
    }
}
