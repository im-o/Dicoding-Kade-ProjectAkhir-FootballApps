package com.stimednp.kadesubmission5.ui.favoritesdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.ui.adapter.ViewPagerAdapter
import com.stimednp.kadesubmission5.ui.favoritesdb.fragment.FavLastmFragment
import com.stimednp.kadesubmission5.ui.favoritesdb.fragment.FavNextmFragment
import com.stimednp.kadesubmission5.ui.favoritesdb.fragment.FavTeamFragment
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        setupViewPager()
        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(tbar_fav)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        tbar_fav.navigationIcon = getDrawable(R.drawable.ic_keyboard_backspace_black_24dp)
        tbar_fav.setNavigationOnClickListener { finish() }
    }

    private fun setupViewPager() {
        val pages = listOf(FavLastmFragment(), FavNextmFragment(), FavTeamFragment())
        val strTab = listOf(R.string.str_last_match, R.string.str_next_match, R.string.str_team)
        vpager_fav.adapter = ViewPagerAdapter(this, strTab, pages, supportFragmentManager)
        vpager_fav.offscreenPageLimit = strTab.size
        tabsl_fav.setupWithViewPager(vpager_fav)
    }
}
