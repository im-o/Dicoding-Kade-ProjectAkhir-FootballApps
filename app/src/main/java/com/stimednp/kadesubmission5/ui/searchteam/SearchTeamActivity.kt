package com.stimednp.kadesubmission5.ui.searchteam

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.presenter.searchteam.SearchTRepository
import com.stimednp.kadesubmission5.ui.adapter.TeamsAdapter
import com.stimednp.kadesubmission5.utils.invisible
import com.stimednp.kadesubmission5.utils.visible
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.support.v4.onRefresh

class SearchTeamActivity : AppCompatActivity(), ISearchTView, SearchView.OnQueryTextListener {
    private lateinit var searchTPresenter: SearchTPresenter
    private lateinit var textSearch: String
    private var teams = ArrayList<DataTeams>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        initialize()
        setToolbar()

        swipe_searcht.setColorSchemeResources(R.color.colorAccent, R.color.colorTwitter, R.color.colorYoutube, R.color.colorFacebook)
        swipe_searcht.onRefresh {
            getSearchData(textSearch)
            clearData()
            rv_searcht.adapter?.notifyDataSetChanged()
        }
    }

    private fun setToolbar() {
        setSupportActionBar(tb_searcht)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tb_searcht.setTitle(R.string.app_title)
        tb_searcht.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp)
        tb_searcht.setNavigationOnClickListener { finish() }
    }

    private fun initialize() {
        showTextEmpty("Search your list team..")
        rv_searcht.layoutManager = LinearLayoutManager(this)
        rv_searcht.adapter = TeamsAdapter(this, teams)
    }

    private fun getSearchData(textSearch: String) {
        searchTPresenter = SearchTPresenter(this, SearchTRepository())
        searchTPresenter.getSearchTeam(textSearch)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_search_menu, menu)
        val searchItem = menu?.findItem(R.id.item_search_action)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.app_search_team)
        searchView.isIconified = false
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        clearData()
        rv_searcht.adapter?.notifyDataSetChanged()
        getSearchData(query)
        textSearch = query
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun clearData() {
        teams.clear()
    }

    override fun showMsgSucces(text: String) {
        //need this for future
    }

    override fun showMsgFail(text: String) {
        //need this for future
    }

    override fun showTextEmpty(text: String) {
        tv_empty_datast.text = text
        tv_empty_datast.visible()
    }

    override fun hideTextEmpty() {
        tv_empty_datast.invisible()
    }

    override fun onDataLoaded(data: ArrayList<DataTeams>) {
        clearData()
        teams.addAll(data)
        rv_searcht?.adapter?.notifyDataSetChanged()
    }

    override fun onDataError() {
        showTextEmpty("No Data...")
    }

    override fun onShowLoading() {
        swipe_searcht.isRefreshing = true
        showTextEmpty("Searching...\nPlease wait...")
    }

    override fun onHideLoading() {
        swipe_searcht.isRefreshing = false
    }
}
