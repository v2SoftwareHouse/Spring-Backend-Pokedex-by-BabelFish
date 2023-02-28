package com.v2.sample.babelfish

import com.v2.sample.babelfish.feature.chain.business.ChainGETBulbasaur
import com.v2.sample.babelfish.feature.chain.business.ChainGETIvysaur
import com.v2.sample.babelfish.feature.chain.business.ChainRepository
import com.v2.sample.babelfish.feature.chain.gateway.ChainGatewayInjector
import com.v2.sample.babelfish.feature.chain.gateway.ChainPresenter
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETBulbasaur
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETIvysaur
import com.v2.sample.babelfish.feature.sequence.business.SequenceGETVenusaur
import com.v2.sample.babelfish.feature.sequence.business.SequenceRepository
import com.v2.sample.babelfish.feature.sequence.gateway.SequenceGatewayInjector
import com.v2.sample.babelfish.feature.sequence.gateway.SequencePresenter
import com.v2.sample.babelfish.feature.unit.business.UnitGET
import com.v2.sample.babelfish.feature.unit.gateway.UnitGatewayInjector
import com.v2.sample.babelfish.feature.unit.gateway.UnitPresenter
import com.v2.sample.babelfish.feature.unit.business.UnitRepository
import com.v2.sample.babelfish.plugin.feature.chain.ChainRepositoryImpl
import com.v2.sample.babelfish.plugin.feature.chain.gateway.ChainPresenterImpl
import com.v2.sample.babelfish.plugin.feature.sequence.SequenceRepositoryImpl
import com.v2.sample.babelfish.plugin.feature.sequence.gateway.SequencePresenterImpl
import com.v2.sample.babelfish.plugin.feature.unit.UnitRepositoryImpl
import com.v2.sample.babelfish.plugin.feature.unit.gateway.UnitPresenterImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokedexByBabelFishApplication {
    init {
        UnitGatewayInjector.self = object : UnitGatewayInjector {
            override val presenter: UnitPresenter
                get() = UnitPresenterImpl(buildUseCase())
        }
        ChainGatewayInjector.self = object : ChainGatewayInjector {
            override val presenter: ChainPresenter
                get() = ChainPresenterImpl(buildChainGETBulbasaur(),
                    buildChainGETIvysaur())
        }
        SequenceGatewayInjector.self = object : SequenceGatewayInjector {
            override val presenter: SequencePresenter
                get() = SequencePresenterImpl(
                    buildSequenceGETBulbasaur(),
                    buildSequenceGETIvysaur(),
                    buildSequenceGETVenusaur()
                )
        }
    }
}

fun main(args: Array<String>) {
    runApplication<PokedexByBabelFishApplication>(*args)
}

private fun buildUseCase() = UnitGET(injectUnitRepository())

private fun buildSequenceGETBulbasaur() = SequenceGETBulbasaur(injectSequenceRepository())

private fun buildSequenceGETIvysaur() = SequenceGETIvysaur(injectSequenceRepository())

private fun buildSequenceGETVenusaur() = SequenceGETVenusaur(injectSequenceRepository())

private fun buildChainGETBulbasaur() = ChainGETBulbasaur(injectChainRepository())

private fun buildChainGETIvysaur() = ChainGETIvysaur(injectChainRepository())


private val baseApiUrl = "https://pokeapi.co/api/v2/"
private fun injectSequenceRepository(): SequenceRepository {
    return SequenceRepositoryImpl(baseApiUrl)
}

private fun injectChainRepository(): ChainRepository {
    return ChainRepositoryImpl(baseApiUrl)
}

private fun injectUnitRepository(): UnitRepository {
    return UnitRepositoryImpl(baseApiUrl)
}