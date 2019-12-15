package com.stimednp.kadesubmission5.ui.detailleagues

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.model.leagues.DataLeagues
import com.stimednp.kadesubmission5.presenter.detailleagues.DetailsLRepository
import com.stimednp.kadesubmission5.ui.adapter.ViewPagerAdapter
import com.stimednp.kadesubmission5.ui.detailleagues.fragementlast.LastMatchFragment
import com.stimednp.kadesubmission5.ui.detailleagues.fragmentnext.NextMatchFragment
import com.stimednp.kadesubmission5.ui.detailleagues.fragmentstanding.StandingFragment
import com.stimednp.kadesubmission5.ui.detailleagues.fragmentteam.TeamFragment
import com.stimednp.kadesubmission5.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.activity_details_leagues.*
import kotlinx.android.synthetic.main.item_header.*
import kotlinx.android.synthetic.main.item_socialmedia.*
import org.jetbrains.anko.toast

class DetailsLeaguesActivity : AppCompatActivity(), IDetailsLView {
    private lateinit var detailsPresenter: DetailsPresenter

    companion object {
        const val EXTRA_ID: String = "extra_data"
        var idLeagues: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_leagues)
        idLeagues = intent.getStringExtra(EXTRA_ID) ?: null
        getLeaguesData(idLeagues)
        setToolbar()
        setupViewPager()
    }

    private fun getLeaguesData(id: String?) {
        detailsPresenter = DetailsPresenter(this, DetailsLRepository())
        detailsPresenter.getLeaguesDetail(id)
    }

    private fun setToolbar() {
        setSupportActionBar(htab_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        htab_toolbar.setTitle(R.string.app_detail_leagues)
        htab_toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp)
        htab_toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupViewPager() { //add fragment to tablayout
        val pages = listOf(LastMatchFragment(), NextMatchFragment(), TeamFragment(), StandingFragment())
        val strTab = listOf(R.string.str_last_match, R.string.str_next_match, R.string.str_team, R.string.str_standing)
//        val strIc = listOf(R.drawable.ic_event_complete_black, R.drawable.ic_event_next_black, R.drawable.ic_event_next_black, R.drawable.ic_event_complete_black)
        val adapter = ViewPagerAdapter(this, strTab, pages, supportFragmentManager)
        htab_viewpager.adapter = adapter
        htab_viewpager.offscreenPageLimit = strTab.size
        htab_tablayout.setupWithViewPager(htab_viewpager)
//        for (i in pages.indices) htab_tablayout.getTabAt(i)?.setIcon(strIc[i])
    }

    private fun showData(data: DataLeagues?) {
        val url = "${data?.strBadge}/preview"
        tv_name_league.text = data?.strLeague
        tv_desc_league.text = data?.strDescriptionEN
        Picasso.get().load(url).into(img_badgeHb)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun goUri(url: String?) {
        if (url == "") {
            toast(getString(R.string.str_nourl))
        } else {
            try {
                val uri = Uri.parse("http://$url")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                Handler().postDelayed({ startActivity(intent) }, 100)
            } catch (e: Exception) {
                toast("Something Error uri : $url")
            }
        }
    }

    private fun addButtonListener(data: DataLeagues) {
        tv_web.setOnClickListener { goUri(data.strWebsite) }
        tv_fb.setOnClickListener { goUri(data.strFacebook) }
        tv_twit.setOnClickListener { goUri(data.strTwitter) }
        tv_yt.setOnClickListener { goUri(data.strYoutube) }
    }


    override fun showMsgSucces(text: String) {
        toast(text)
    }

    override fun showMsgFail(text: String) {
        toast(text)
    }

    override fun onDataLoaded(data: DataLeagues) {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            //task is complete -> DELETE this after test (Memory leak)
            EspressoIdlingResource.decrement()
        }
        showData(data)
        addButtonListener(data)
    }

    override fun onDataError() {
        toast("Error or No Data")
        //need this for future
    }

    override fun onShowLoading() {
        //need this for future
    }

    override fun onHideLoading() {
        //need this for future
    }
}
