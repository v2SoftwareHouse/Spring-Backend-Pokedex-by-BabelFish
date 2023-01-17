package com.v2.sample.babelfish.feature.landing.business

import com.v2.babelfish.business.dto.Output
import com.v2.babelfish.business.dto.ValueOutput
import com.v2.babelfish.business.interactor.UseCase

open class LandingUseCase(
        private val repo: LandingRepository
) : UseCase<String, Any>() {

    override fun execute(param: String?): Output<Any> {
        val landing = repo.doFetch(param)
        return ValueOutput(landing)
    }
}