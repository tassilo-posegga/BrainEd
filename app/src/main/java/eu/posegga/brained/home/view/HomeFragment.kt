package eu.posegga.brained.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import eu.posegga.brained.R
import eu.posegga.brained.home.domain.model.Level
import eu.posegga.brained.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val homeViewModel by sharedViewModel<HomeViewModel>()

    private val levelAdapter = LevelAdapter(::onLevelClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        levelList.apply {
            adapter = levelAdapter
            layoutManager = GridLayoutManager(context, COLUMN_COUNT)
            hasFixedSize()
        }

        homeViewModel.levels.observe(viewLifecycleOwner, Observer(::observeLevels))
        homeViewModel.loadLevels()
    }

    private fun observeLevels(levels: List<Level>) {
        levelAdapter.levels = levels
        levelAdapter.notifyDataSetChanged()
    }

    private fun onLevelClick(level: Level) {
        findNavController().navigate(level.fragment)
    }

    private companion object {
        const val COLUMN_COUNT = 3
    }
}
