package com.geekbrains.cleancodeapp.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.cleancodeapp.model.data.AppState
import com.geekbrains.cleancodeapp.utils.parseLocalSearchResults
import com.geekbrains.cleancodeapp.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel (private val interactor: HystoryInteractor):
BaseViewModel<AppState> () {


    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}