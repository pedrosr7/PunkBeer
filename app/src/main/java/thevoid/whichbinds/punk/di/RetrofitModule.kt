package thevoid.whichbinds.punk.di

import thevoid.whichbinds.punk.BuildConfig.API_PUNK
import thevoid.whichbinds.punk.data.datasource.network.ApiService
import thevoid.whichbinds.punk.data.datasource.network.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {

    single {
        Retrofit.Builder()
                .baseUrl(API_PUNK)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
    }

    single {
        get<Retrofit>().create(ApiService::class.java)   // 5
    }

}


private val loggingInterceptor = HttpLoggingInterceptor().apply {
    this.level = HttpLoggingInterceptor.Level.BODY
}

private val httpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .addNetworkInterceptor(AuthInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()
}