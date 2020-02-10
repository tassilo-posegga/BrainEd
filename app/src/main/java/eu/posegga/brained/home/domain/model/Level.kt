package eu.posegga.brained.home.domain.model

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

data class Level(
    val id: String,
    val name: String,
    val imgSrc: Int,
    val description: String,
    @LayoutRes
    val customView: Int,
    @IdRes
    val fragment: Int
)
