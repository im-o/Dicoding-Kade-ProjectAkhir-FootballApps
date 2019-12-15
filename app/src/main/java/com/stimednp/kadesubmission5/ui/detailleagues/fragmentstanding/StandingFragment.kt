package com.stimednp.kadesubmission5.ui.detailleagues.fragmentstanding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.model.standings.DataStandings
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentstanding.StandingsRepository
import com.stimednp.kadesubmission5.ui.adapter.StandingAdapter
import com.stimednp.kadesubmission5.ui.detailleagues.DetailsLeaguesActivity
import com.stimednp.kadesubmission5.utils.invisible
import com.stimednp.kadesubmission5.utils.visible
import kotlinx.android.synthetic.main.fragment_standing.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class StandingFragment : Fragment(), IStandingView {
    lateinit var standingPresenter: StandingPresenter
    private var tables = ArrayList<DataStandings>()
    private var badges = ArrayList<DataTeamsBadge>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val idLeague = DetailsLeaguesActivity.idLeagues
        rv_standings.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_standings.adapter = StandingAdapter(tables, badges)
        getStandingsData(idLeague)
    }

    private fun getStandingsData(idLeague: String?) {
        standingPresenter = StandingPresenter(this, StandingsRepository())
        standingPresenter.getStandings(idLeague.toString())
    }

    override fun showMsgSucces(text: String) {
    }

    override fun showMsgFail(text: String) {
    }

    override fun showTextEmpty(text: String) {
        tv_empty_stand.visible()
    }

    override fun hideTextEmpty() {
        tv_empty_stand.invisible()
    }

    override fun onDataLoaded(data: ArrayList<DataStandings>, badge: ArrayList<DataTeamsBadge>) {
        tables.clear()
        badges.clear()
        tables.addAll(data)
        badges.addAll(badge)
        rv_standings?.adapter?.notifyDataSetChanged()
    }

    override fun onDataError() {
        toast("Error or No Data")
    }

    override fun onShowLoading() {
        progress_stand.visible()
    }

    override fun onHideLoading() {
        progress_stand.invisible()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.item_search)
        if (!menuItem.equals(null)) menuItem.isVisible = false
    }
}
