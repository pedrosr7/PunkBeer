package thevoid.whichbinds.punk.data.datasource.implementation

import arrow.core.Either
import thevoid.whichbinds.punk.core.exceptions.MyFailure
import thevoid.whichbinds.punk.data.datasource.RemoteDataSource
import thevoid.whichbinds.punk.data.datasource.mapper.toDomain
import thevoid.whichbinds.punk.data.datasource.network.ApiService
import thevoid.whichbinds.punk.domain.models.BeerListing

class RemoteDataSourceImpl constructor(
    private val apiService: ApiService
): RemoteDataSource {
    override suspend fun getBeers(name: String?): Either<MyFailure, BeerListing> {
        val response = apiService.fetchBeers(name)
        return if(response.isSuccessful) {
            try {
                Either.Right(BeerListing(response.body()!!.map { it.toDomain() }))
            } catch (e: Exception) {
                Either.Left(MyFailure.InvalidObject)
            }

        } else {
            Either.Left(MyFailure.ServerError)
        }
    }

}