package eu.posegga.brained.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.posegga.brained.R
import eu.posegga.brained.domain.model.Level
import eu.posegga.brained.extensions.loadFromUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.level_item.view.*

class LevelAdapter : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

    var levels: List<Level> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder =
        LevelViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.level_item, parent, false))

    override fun getItemCount(): Int =
        levels.size

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) =
        holder.bind(levels[position])

    class LevelViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(level: Level) {
            containerView.levelName.text = level.name
            containerView.levelImage.loadFromUrl(level.imgSrc)
        }
    }
}