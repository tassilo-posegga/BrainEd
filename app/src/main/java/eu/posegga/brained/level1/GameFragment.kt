package eu.posegga.brained.level1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import eu.posegga.brained.R
import eu.posegga.brained.home.domain.model.Level
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

        val currentLevelView =
            LayoutInflater.from(context).inflate(level.customView, contentContainer, false)
        contentContainer.addView(currentLevelView)

        currentLevelViewModel.gameState.observe(
            viewLifecycleOwner,
            Observer { gameStateChanged(it, currentLevelView) })

        currentLevelViewModel.start()
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
