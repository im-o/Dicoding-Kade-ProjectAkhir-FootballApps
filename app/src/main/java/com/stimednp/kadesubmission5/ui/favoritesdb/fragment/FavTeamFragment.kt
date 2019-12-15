package com.stimednp.kadesubmission5.ui.favoritesdb.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.db.MydbOpenHelper.databaseTeams
import com.stimednp.kadesubmission5.model.db.DataFavoriteTeam
import com.stimednp.kadesubmission5.ui.adapter.FavTeamAdapter
import com.stimednp.kadesubmission5.utils.visible
import kotlinx.android.synthetic.main.fragment_fav_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class FavTeamFragment : Fragment() {
    private var itemFavorites: ArrayList<DataFavoriteTeam> = arrayListOf()
    private var adapter: FavTeamAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial()
        swipe_teamfav.isRefreshing = true
        swipe_teamfav.setColorSchemeResources(R.color.colorAccent, R.color.colorTwitter, R.color.colorYoutube, R.color.colorFacebook)
        swipe_teamfav.onRefresh { showFavorite() }
    }

    private fun initial() {
        rv_teamfav.layoutManager = LinearLayoutManager(context)
        adapter = FavTeamAdapter(context, itemFavorites)
        rv_teamfav.adapter = adapter
    }

    private fun showFavorite() {
        itemFavorites.clear()
        context?.databaseTeams?.use {
            val result = select(DataFavoriteTeam.TABLE_FAVTEAM)
            val favorite = result.parseList(classParser<DataFavoriteTeam>())
            itemFavorites.addAll(favorite)
            adapter?.notifyDataSetChanged()
        }
        if (itemFavorites.size <= 0) enableNoData()
        disableLoad()
    }

    private fun disableLoad() {
        if (swipe_teamfav.isRefreshing) {
            swipe_teamfav.isRefreshing = false
        }
    }

    private fun enableNoData() {
        tv_teamfav_empty.visible()
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
}
