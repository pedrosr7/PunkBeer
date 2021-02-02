package thevoid.whichbinds.punk.presentation.beer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import thevoid.whichbinds.punk.core.plataform.BaseViewModel
import thevoid.whichbinds.punk.core.plataform.SingleLiveEvent
import thevoid.whichbinds.punk.domain.models.Beer
import thevoid.whichbinds.punk.domain.usecases.GetBeersUseCases

class BeersViewModel(
    private val getBeersUseCases: GetBeersUseCases
) : BaseViewModel() {

    val showLoading = SingleLiveEvent<Boolean>()
    val showErrorObserver = SingleLiveEvent<Throwable>()

    private val _beers: MutableLiveData<List<Beer>> by lazy {
        MutableLiveData<List<Beer>>()
    }
    val observerBeers: MutableLiveData<List<Beer>> = _beers

    fun getBeers(name: String?) {
        showLoading.postValue(true)
        getBeersUseCases.invoke(viewModelScope, name) { res ->
            showLoading.postValue(false)
            res.fold(
                ifLeft = { showErrorObserver.postValue(it) },
                ifRight = { _beers.postValue(it.beers) }
            )
        }
    }

}