package eu.posegga.brained.domain.usecase

import eu.posegga.brained.data.LevelRepository

class LoadLevelsUseCase(
    private val repository: LevelRepository
) {

    fun loadLevels() =
        repository.loadLevels()
}
