package com.v2.sample.babelfish.feature.unit.business

interface UnitRepository {
    fun doFetch(name:String?): Any?
}