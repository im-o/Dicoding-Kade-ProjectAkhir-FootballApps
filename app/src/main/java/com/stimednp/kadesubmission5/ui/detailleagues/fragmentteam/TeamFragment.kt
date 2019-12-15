package com.stimednp.kadesubmission5.ui.detailleagues.fragmentteam


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentteam.TeamRepository
import com.stimednp.kadesubmission5.ui.adapter.TeamsAdapter
import com.stimednp.kadesubmission5.ui.detailleagues.DetailsLeaguesActivity
import com.stimednp.kadesubmission5.ui.searchteam.SearchTeamActivity
import com.stimednp.kadesubmission5.utils.invisible
import com.stimednp.kadesubmission5.utils.visible
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment : Fragment(), ITeamView {
    private lateinit var teamPresenter: TeamPresenter
    private val listTeams = ArrayList<DataTeams>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val idLeague = DetailsLeaguesActivity.idLeagues
        getListTeam(idLeague.toString())
        rv_teamleague.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_teamleague.adapter = TeamsAdapter(context, listTeams)
    }

    private fun getListTeam(idLeague: String) {
        teamPresenter = TeamPresenter(this, TeamRepository())
        teamPresenter.getListTeam(idLeague)

    }

    override fun showMsgSucces(text: String) {

    }

    override fun showMsgFail(text: String) {
    }

    override fun showTextEmpty(text: String) {
        tv_empty_teamleague.visible()
    }

    override fun hideTextEmpty() {
        tv_empty_teamleague.invisible()
    }

    override fun onDataLoaded(data: ArrayList<DataTeams>) {
        listTeams.clear()
        listTeams.addAll(data)
        rv_teamleague.adapter?.notifyDataSetChanged()
    }

    override fun onDataError() {
        toast("Error or No Data")
    }

    override fun onShowLoading() {
        progress_teamleague.visible()
    }

    override fun onHideLoading() {
        progress_teamleague.invisible()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.item_search)
        menuItem.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_search -> startActivity<SearchTeamActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}
