package eu.posegga.brained.home.data

import eu.posegga.brained.home.domain.model.Level
import eu.posegga.brained.home.local.repository.LevelLocalSource
import io.reactivex.Single

class LevelRepository(
    private val local: LevelLocalSource
) {

    fun loadLevels(): Single<List<Level>> =
        local.loadLevels()
}
