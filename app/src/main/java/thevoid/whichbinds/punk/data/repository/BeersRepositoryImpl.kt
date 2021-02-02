package thevoid.whichbinds.punk.data.repository

import arrow.core.Either
import thevoid.whichbinds.punk.core.exceptions.MyFailure
import thevoid.whichbinds.punk.core.managers.NetworkManager
import thevoid.whichbinds.punk.data.datasource.RemoteDataSource
import thevoid.whichbinds.punk.domain.models.BeerListing
import thevoid.whichbinds.punk.domain.repository.BeerRepository

class BeersRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val networkManager: NetworkManager
): BeerRepository {
    override suspend fun getBeers(name: String?): Either<MyFailure, BeerListing> {
        return when (networkManager.isNetworkAvailable) {
            true -> remoteDataSource.getBeers(name)
            false -> Either.Left(MyFailure.NetworkConnectionError)
        }
    }
}