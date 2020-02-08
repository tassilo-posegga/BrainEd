package eu.posegga.brained.home.domain.usecase

import eu.posegga.brained.home.data.LevelRepository

class LoadLevelsUseCase(
    private val repository: LevelRepository
) {

    fun loadLevels() =
        repository.loadLevels()
}
