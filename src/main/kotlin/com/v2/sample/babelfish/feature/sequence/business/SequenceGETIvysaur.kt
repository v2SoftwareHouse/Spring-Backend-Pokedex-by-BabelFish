package com.v2.sample.babelfish.feature.sequence.business

import com.v2.babelfish.business.dto.Output
import com.v2.babelfish.business.dto.ValueOutput
import com.v2.babelfish.business.interactor.UseCase

open class SequenceGETIvysaur(
        private val repo: SequenceRepository
) : UseCase<String, Any>() {

    override fun execute(param: String?): Output<Any> {
        val sequence = repo.getIvysaur()
        return ValueOutput(sequence)
    }
}
