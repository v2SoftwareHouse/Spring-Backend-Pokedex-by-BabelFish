package com.v2.sample.babelfish.feature.landing.business

interface LandingRepository {
    fun doFetch(name:String?): Any?
}