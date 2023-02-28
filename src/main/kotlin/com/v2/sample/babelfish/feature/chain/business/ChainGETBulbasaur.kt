package com.v2.sample.babelfish.feature.chain.business

import com.v2.babelfish.business.dto.Output
import com.v2.babelfish.business.dto.ValueOutput
import com.v2.babelfish.business.interactor.UseCase

open class ChainGETBulbasaur(
    private val repo: ChainRepository
) : UseCase<Void, MutableList<Any?>>() {
    override fun execute(param: Void?): Output<MutableList<Any?>> {
        val bulbasaur = repo.getBulbasaur()
        val chain = mutableListOf(bulbasaur)

        return ValueOutput(chain)
    }
}
