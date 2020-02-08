package eu.posegga.brained.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.posegga.brained.home.domain.model.Level
import eu.posegga.brained.home.domain.usecase.LoadLevelsUseCase
import eu.posegga.brained.home.extensions.subscribeOnIoObserveOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class HomeViewModel(
    private val loadLevelsUseCase: LoadLevelsUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _levels = MutableLiveData<List<Level>>()
    val levels: LiveData<List<Level>>
        get() = _levels

    fun loadLevels() {
        disposables += loadLevelsUseCase.loadLevels()
            .subscribeOnIoObserveOnMain()
            .subscribeBy(
                onError = Timber::e,
                onSuccess = { _levels.value = it }
            )
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    fun levelClicked(level: Level) {

    }
}




