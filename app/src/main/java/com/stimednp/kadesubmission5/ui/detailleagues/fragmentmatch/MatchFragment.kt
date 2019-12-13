package com.stimednp.kadesubmission5.ui.detailleagues.fragmentmatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.ui.detailleagues.fragementlast.LastMatchFragment
import com.stimednp.kadesubmission5.ui.detailleagues.fragmentnext.NextMatchFragment

/**
 * A simple [Fragment] subclass.
 */
class MatchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentMatch = listOf<Fragment>(LastMatchFragment(), NextMatchFragment())
        val idFragment = listOf(R.id.container_last_match, R.id.container_next_match)
        for (i in fragmentMatch.indices) childFragmentManager.beginTransaction()
            .replace(idFragment[i], fragmentMatch[i], fragmentMatch[i]::class.java.simpleName)
            .commit()
    }
}
