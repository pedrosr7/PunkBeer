package thevoid.whichbinds.punk.domain.usecases

import arrow.core.Either
import thevoid.whichbinds.punk.core.exceptions.MyFailure
import thevoid.whichbinds.punk.core.plataform.BaseParamsUseCase
import thevoid.whichbinds.punk.domain.models.BeerListing
import thevoid.whichbinds.punk.domain.repository.BeerRepository

class GetBeersUseCases(
    private val beerRepository: BeerRepository
): BaseParamsUseCase<BeerListing, String?>() {
    override suspend fun run(params: String?): Either<MyFailure, BeerListing> = beerRepository.getBeers(params)
}