package com.v2.sample.babelfish.plugin.feature.unit.gateway

import com.v2.babelfish.controller.BaseController
import com.v2.sample.babelfish.feature.unit.business.UnitGET
import com.v2.sample.babelfish.feature.unit.gateway.UnitPresenter

class UnitPresenterImpl(private val fetcher: UnitGET)
    : BaseController(), UnitPresenter {

    override fun doFetch(name: String?): Any? {
        return processUseCase(name, fetcher)?.value
    }
}