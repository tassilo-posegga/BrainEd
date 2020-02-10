package eu.posegga.brained.home.local.repository

import eu.posegga.brained.R
import eu.posegga.brained.home.domain.model.Level
import io.reactivex.Single

class LevelLocalSource {

    fun loadLevels() =
        Single.just(staticLevels)

    fun loadLevelById(id: String): Single<Level> =
        Single.just(staticLevels.find { it.id == id })

    private companion object {
        val staticLevels = listOf(
            Level(
                "1",
                "Sortieren",
                R.drawable.ic_sort,
                "Sortiere von klein nach groß",
                R.layout.level1_custom_view,
                R.id.level1Fragment
            )
        )
    }
}
