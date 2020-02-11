package eu.posegga.brained.level1

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import eu.posegga.brained.R
import eu.posegga.brained.home.domain.model.Level
import eu.posegga.brained.home.extensions.pop
import eu.posegga.brained.home.view.CircleView
import eu.posegga.brained.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.game_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GameFragment : Fragment() {

    private val homeViewModel by sharedViewModel<HomeViewModel>()

    private val rows by lazy { resources.getInteger(R.integer.rows) }
    private val columns by lazy { resources.getInteger(R.integer.columns) }
    private val cells by lazy { rows * columns }
    private lateinit var availableRadius: MutableList<Float>
    private lateinit var shuffled: MutableList<Float>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.game_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.currentLevel.observe(viewLifecycleOwner, Observer(::startLevel))
        homeViewModel.loadLevelById("1")

        availableRadius =
            generateSequence(10f) { it + 6 }.take(cells).toList().reversed().toMutableList()
        shuffled = availableRadius.shuffled().toMutableList()
    }

    private fun startLevel(level: Level) {
        description.text = level.description

        val currentLevelView = linearLayout(VERTICAL, 0)

        for (row in 1..rows) {
            val rowLayout = linearLayout(HORIZONTAL, 0)

            for (column in 1..columns) {
                shuffled.pop()?.let { rndRadius ->
                    val cellLayout = linearLayout(VERTICAL, rndRadius.toInt())
                    cellLayout.setOnClickListener { onCellClicked(rndRadius.toInt()) }
                    cellLayout.addView(
                        CircleView(context).apply {
                            radius = rndRadius
                        }
                    )
                    rowLayout.addView(cellLayout)
                }
            }
            currentLevelView.addView(rowLayout)
        }

        contentContainer.addView(currentLevelView)
    }

    private fun onCellClicked(cellId: Int) {
        availableRadius.pop()?.let {
            if (it.toInt() != cellId) {
                markCorrectCell(it.toInt())
                showLostScreen()
            } else {
                hideCell(cellId)
            }
            if (availableRadius.isEmpty()) {
                showWinScreen()
            }
        }
    }

    private fun markCorrectCell(cellId: Int) {
        getCellCircle(cellId)?.apply {
            circleColor = Color.GREEN
        }
    }

    private fun hideCell(cellId: Int) {
        getCellCircle(cellId)?.apply {
            visibility = View.INVISIBLE
        }
    }

    private fun getCellCircle(cellId: Int): CircleView? =
        contentContainer.findViewById<LinearLayout>(cellId).getChildAt(0) as? CircleView

    private fun linearLayout(
        orient: Int,
        cellId: Int = 0
    ): LinearLayout =
        LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
                weight = 1f
                orientation = orient
                id = cellId
                gravity = Gravity.CENTER
            }
        }


    private fun showWinScreen() {
        findNavController().navigate(R.id.action_level1Fragment_to_wonFragment)
    }

    private fun showLostScreen() {
        findNavController().navigate(R.id.action_level1Fragment_to_lostFragment)
    }
}
