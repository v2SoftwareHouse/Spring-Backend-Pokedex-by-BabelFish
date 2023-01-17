package com.v2.sample.babelfish

import com.v2.sample.babelfish.feature.landing.business.LandingUseCase
import com.v2.sample.babelfish.feature.landing.gateway.LandingGatewayInjector
import com.v2.sample.babelfish.feature.landing.gateway.LandingPresenter
import com.v2.sample.babelfish.feature.landing.business.LandingRepository
import com.v2.sample.babelfish.plugin.feature.landing.LandingRepositoryImpl
import com.v2.sample.babelfish.plugin.feature.landing.gateway.LandingPresenterImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokedexByBabelFishApplication {
	init {
		LandingGatewayInjector.self = object : LandingGatewayInjector {
			override val presenter: LandingPresenter
				get() = LandingPresenterImpl(buildUseCase())
		}
	}
}

fun main(args: Array<String>) {
	runApplication<PokedexByBabelFishApplication>(*args)
}

private fun buildUseCase() = LandingUseCase(injectCacheRepository())

private fun injectCacheRepository(): LandingRepository {
	val baseApiUrl = "https://pokeapi.co/api/v2/"
	return LandingRepositoryImpl(baseApiUrl)
}