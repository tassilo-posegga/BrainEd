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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

        val rows = 5
        val columns = 4
        val cells = rows * columns

        val availableRadius = generateSequence(10f) { it + 6 }.take(cells).toList().shuffled()

        val currentLevelView = linearLayout(VERTICAL, 0)

        for (row in 1..rows) {
            val rowLayout = linearLayout(HORIZONTAL, 0)

            for (column in 1..columns) {
                val id = row * column
                val cellLayout = linearLayout(VERTICAL, id, ::onCellClicked)
                cellLayout.addView(
                    CircleView(context).apply {
                        radius = availableRadius[id - 1]
                    }
                )
                rowLayout.addView(cellLayout)
            }

            currentLevelView.addView(rowLayout)

        }

        contentContainer.addView(currentLevelView)

        currentLevelViewModel.gameState.observe(
            viewLifecycleOwner,
            Observer { gameStateChanged(it, currentLevelView) })

        currentLevelViewModel.start()
    }

    private fun onCellClicked(cellId: @ParameterName(name = "id") Int) {
        Toast.makeText(context, "$cellId", Toast.LENGTH_SHORT).show()
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showLostScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
