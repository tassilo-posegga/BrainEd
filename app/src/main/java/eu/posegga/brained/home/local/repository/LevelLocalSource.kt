package eu.posegga.brained.home.local.repository

import eu.posegga.brained.R
import eu.posegga.brained.home.domain.model.Level
import io.reactivex.Single

class LevelLocalSource {

    fun loadLevels() =
        Single.just(staticLevels)

    private companion object {
        val staticLevels = listOf(
            Level(1, "Sortieren", R.drawable.ic_sort)
        )
    }
}