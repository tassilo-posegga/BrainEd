package eu.posegga.brained.level1

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
import eu.posegga.brained.home.view.CircleView
import eu.posegga.brained.home.viewmodel.GameState
import eu.posegga.brained.home.viewmodel.HomeViewModel
import eu.posegga.brained.home.viewmodel.Level1ViewModel
import kotlinx.android.synthetic.main.game_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GameFragment : Fragment() {

    private val homeViewModel by sharedViewModel<HomeViewModel>()
    private val currentLevelViewModel by sharedViewModel<Level1ViewModel>()

    private val rows = 5
    private val columns = 4
    private val cells = rows * columns
    private val availableRadius =
        generateSequence(10f) { it + 6 }.take(cells).toList().reversed().toMutableList()
    private val shuffled = availableRadius.shuffled().toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.game_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.loadLevelById("1")
        homeViewModel.currentLevel.observe(viewLifecycleOwner, Observer(::startLevel))
    }

    private fun startLevel(level: Level) {
        description.text = level.description

        val currentLevelView = linearLayout(VERTICAL, 0)

        var cellId = 0
        for (row in 1..rows) {
            val rowLayout = linearLayout(HORIZONTAL, 0)

            for (column in 1..columns) {
                shuffled.pop()?.let {
                    val cellLayout = linearLayout(VERTICAL, it.toInt(), ::onCellClicked)
                    cellLayout.addView(
                        CircleView(context).apply {
                            radius = it
                        }
                    )
                    cellId++
                    rowLayout.addView(cellLayout)
                }
            }
            currentLevelView.addView(rowLayout)
        }

        contentContainer.addView(currentLevelView)

        currentLevelViewModel.gameState.observe(
            viewLifecycleOwner,
            Observer { gameStateChanged(it, currentLevelView) })

        currentLevelViewModel.start()
    }

    private fun onCellClicked(cellId: Int) {
        availableRadius.pop()?.let {
            if (it.toInt() != cellId) {
                showLostScreen()
            } else {
                hideCell(cellId)
            }
        } ?: showWinScreen()
    }

    private fun hideCell(cellId: Int) {
        contentContainer.findViewById<View>(cellId).apply {
            visibility = View.INVISIBLE
        }
    }

    private fun linearLayout(
        orient: Int,
        cellId: Int = 0,
        clickListener: (id: Int) -> Unit = {}
    ): LinearLayout =
        LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
                weight = 1f
                orientation = orient
                id = cellId
                gravity = Gravity.CENTER
            }
            setOnClickListener { clickListener.invoke(cellId) }
        }

    private fun gameStateChanged(
        gameState: GameState,
        currentLevelView: View
    ) {
        when (gameState) {
            GameState.LOST -> showLostScreen()
            GameState.LOADING -> showLoading()
            GameState.WON -> showWinScreen()
        }
    }

    private fun showWinScreen() {
        findNavController().navigate(R.id.action_level1Fragment_to_wonFragment)
    }

    private fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showLostScreen() {
        findNavController().navigate(R.id.action_level1Fragment_to_lostFragment)
    }


    fun <T> MutableList<T>.pop(): T? =
        if (this.count() > 0) this.removeAt(this.count() - 1) else null
}
