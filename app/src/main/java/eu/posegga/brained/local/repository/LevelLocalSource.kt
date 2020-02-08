package eu.posegga.brained.local.repository

import eu.posegga.brained.domain.model.Level
import io.reactivex.Single

class LevelLocalSource {

    fun loadLevels() =
        Single.just(staticLevels)

    private companion object {
        val staticLevels = listOf(
            Level(1, "Level 1", "https://placekitten.com/g/200/200")
        )
    }
}
