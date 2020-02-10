package eu.posegga.brained.home.domain.usecase

import eu.posegga.brained.home.data.LevelRepository

class LoadLevelByIdUseCase(
    private val repository: LevelRepository
) {

    fun loadLevelById(id: String) =
        repository.loadLevelById(id)
}
