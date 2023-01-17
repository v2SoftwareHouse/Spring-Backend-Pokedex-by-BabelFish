package com.v2.sample.babelfish.plugin.feature.landing.gateway

import com.v2.babelfish.controller.BaseController
import com.v2.sample.babelfish.feature.landing.business.LandingUseCase
import com.v2.sample.babelfish.feature.landing.gateway.LandingPresenter

class LandingPresenterImpl(private val fetcher: LandingUseCase) : BaseController(), LandingPresenter {

    override fun doFetch(name: String?): Any? {
        return processUseCase(name, fetcher)?.value
    }
}