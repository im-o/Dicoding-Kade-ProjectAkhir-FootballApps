package com.stimednp.kadesubmission5.ui.detailleagues.fragmentstanding


import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
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
    }

    override fun hideTextEmpty() {
    }

    override fun onDataLoaded(data: ArrayList<DataStandings>, badge: ArrayList<DataTeamsBadge>) {
        tables.clear()
        badges.clear()
        tables.addAll(data)
        badges.addAll(badge)
        rv_standings?.adapter?.notifyDataSetChanged()
        toast("Succes")
        e("INIII","Standings : ${data.size} ")
        e("INIII","Badge : ${badge[0]}")
    }

    override fun onDataError() {
        toast("ERORRR")
    }

    override fun onShowLoading() {
    }

    override fun onHideLoading() {
    }
}
