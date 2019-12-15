package com.stimednp.kadesubmission5.ui.detailleagues.fragmentnext


//import kotlinx.android.synthetic.main.fragment_last_match.*
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.model.events.DataNextMatch
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentnext.NextRepository
import com.stimednp.kadesubmission5.ui.adapter.NextMatchAdapter
import com.stimednp.kadesubmission5.ui.detailleagues.DetailsLeaguesActivity
import com.stimednp.kadesubmission5.ui.search.SearchActivity
import com.stimednp.kadesubmission5.utils.EspressoIdlingResource
import com.stimednp.kadesubmission5.utils.invisible
import com.stimednp.kadesubmission5.utils.visible
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment(), INextMatchView {
    private lateinit var nextMatchPresenter: NextMatchPresenter
    private var itemEvents = ArrayList<DataNextMatch>()
    private var itemTeamsH = ArrayList<DataTeamsBadge>()
    private var itemTeamsA = ArrayList<DataTeamsBadge>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val idLeague = DetailsLeaguesActivity.idLeagues
        getNextMatch(idLeague)
        rv_nextmatch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_nextmatch.adapter = NextMatchAdapter(context, itemEvents, itemTeamsH, itemTeamsA)
    }

    private fun getNextMatch(idLeague: String?) {
        nextMatchPresenter = NextMatchPresenter(this, NextRepository())
        nextMatchPresenter.getNextMatch(idLeague.toString())
    }

    override fun showMsgSucces(text: String) {
        toast(text)
    }

    override fun showMsgFail(text: String) {
        toast(text)
    }

    override fun showTextEmpty(text: String) {
        tv_empty_nextmatch.text = text
        tv_empty_nextmatch.visible()
    }

    override fun hideTextEmpty() {
        tv_empty_nextmatch.invisible()
    }

    override fun onDataLoaded(data: ArrayList<DataNextMatch>, itemsH: ArrayList<DataTeamsBadge>, itemsA: ArrayList<DataTeamsBadge>) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            //task is complete -> DELETE this after test (Memory leak)
            EspressoIdlingResource.decrement()
        }
        itemEvents.clear()
        itemTeamsH.clear()
        itemTeamsA.clear()
        itemEvents.addAll(data)
        itemTeamsH.addAll(itemsH)
        itemTeamsA.addAll(itemsA)
        rv_nextmatch.adapter?.notifyDataSetChanged()
    }

    override fun onDataError() {
        showTextEmpty("Something Error! or No Data!")
    }

    override fun onShowLoading() {
        showTextEmpty("Load data\nPlease wait..")
        progress_nextmatch.visible()
    }

    override fun onHideLoading() {
        progress_nextmatch.invisible()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.item_search)
        menuItem.isVisible = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_search -> startActivity<SearchActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}
