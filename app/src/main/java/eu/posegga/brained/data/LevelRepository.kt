package eu.posegga.brained.data

import eu.posegga.brained.domain.model.Level
import eu.posegga.brained.local.repository.LevelLocalSource
import io.reactivex.Single

class LevelRepository(
    private val local: LevelLocalSource
) {

    fun loadLevels(): Single<List<Level>> =
        local.loadLevels()
}
