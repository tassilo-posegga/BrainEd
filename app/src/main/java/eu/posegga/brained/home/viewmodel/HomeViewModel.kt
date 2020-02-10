package eu.posegga.brained.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.posegga.brained.home.domain.model.Level
import eu.posegga.brained.home.domain.usecase.LoadLevelByIdUseCase
import eu.posegga.brained.home.domain.usecase.LoadLevelsUseCase
import eu.posegga.brained.home.extensions.subscribeOnIoObserveOnMain
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class HomeViewModel(
    private val loadLevelsUseCase: LoadLevelsUseCase,
    private val loadLevelByIdUseCase: LoadLevelByIdUseCase
) : BaseViewModel() {

    private val _levels = MutableLiveData<List<Level>>()
    val levels: LiveData<List<Level>>
        get() = _levels

    private val _currentLevel = MutableLiveData<Level>()
    val currentLevel: LiveData<Level>
        get() = _currentLevel

    fun loadLevels() {
        disposables += loadLevelsUseCase.loadLevels()
            .subscribeOnIoObserveOnMain()
            .subscribeBy(
                onError = Timber::e,
                onSuccess = { _levels.value = it }
            )
    }

    fun loadLevelById(id: String) {
        disposables += loadLevelByIdUseCase.loadLevelById(id)
            .subscribeOnIoObserveOnMain()
            .subscribeBy(
                onError = Timber::e,
                onSuccess = { _currentLevel.value = it }
            )
    }
}




