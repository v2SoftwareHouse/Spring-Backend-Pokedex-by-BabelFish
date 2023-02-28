package com.v2.sample.babelfish.feature.landing.gateway

interface LandingPresenter {
    fun doFetch(name: String?): Any?
}