package com.v2.sample.babelfish.feature.unit.business

import com.v2.babelfish.business.dto.Output
import com.v2.babelfish.business.dto.ValueOutput
import com.v2.babelfish.business.interactor.UseCase

open class UnitGET(
        private val repo: UnitRepository
) : UseCase<String, Any>() {

    override fun guard(param: String?): Boolean {
        return !param.isNullOrEmpty()
    }
    override fun execute(param: String?): Output<Any> {
        val unit = repo.doFetch(param)
        return ValueOutput(unit)
    }
}
