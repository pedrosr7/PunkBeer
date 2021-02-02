package thevoid.whichbinds.punk.di

import thevoid.whichbinds.punk.core.MyApplication
import thevoid.whichbinds.punk.core.managers.NetworkManager
import thevoid.whichbinds.punk.data.datasource.RemoteDataSource
import thevoid.whichbinds.punk.data.datasource.implementation.RemoteDataSourceImpl
import thevoid.whichbinds.punk.data.repository.BeersRepositoryImpl
import thevoid.whichbinds.punk.domain.repository.BeerRepository
import thevoid.whichbinds.punk.domain.usecases.GetBeersUseCases
import thevoid.whichbinds.punk.presentation.beer.viewmodel.BeersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val appModule = module {

    single { MyApplication() }

    single {
        NetworkManager(
            androidContext()
        )
    }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<BeerRepository> { BeersRepositoryImpl(get(), get()) }
    single { GetBeersUseCases(get()) }
    viewModel { BeersViewModel(get()) }

}

