package thevoid.whichbinds.punk.data.datasource.network

import thevoid.whichbinds.punk.data.datasource.dto.NetworkBeer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("beers")
    suspend fun fetchBeers(
        @Query("beer_name") name: String? = null
    ): Response<List<NetworkBeer>>

}