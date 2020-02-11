package eu.posegga.brained.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import eu.posegga.brained.R
import kotlinx.android.synthetic.main.won_fragment.*

class WonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.won_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retryButton.setOnClickListener { findNavController().navigate(R.id.action_wonFragment_to_level1Fragment) }
        menuButton.setOnClickListener { findNavController().navigate(R.id.action_wonFragment_to_homeFragment) }
    }

}
