package thevoid.whichbinds.punk.domain.repository

import arrow.core.Either
import thevoid.whichbinds.punk.core.exceptions.MyFailure
import thevoid.whichbinds.punk.data.datasource.dto.NetworkBeer
import thevoid.whichbinds.punk.domain.models.BeerListing
import retrofit2.Response

/**
 *
 * Handles beer request
 *
 */
interface BeerRepository {

    /**
     * Function that get beers by name
     *
     * @param name Beer name
     *
     */
    suspend fun getBeers(name: String?): Either<MyFailure, BeerListing>

}