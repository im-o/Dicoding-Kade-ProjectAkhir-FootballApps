package com.stimednp.kadesubmission5.ui.favoritesdb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.db.MydbOpenHelper.databaseNext
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent
import com.stimednp.kadesubmission5.ui.adapter.FavoriteAdapter
import com.stimednp.kadesubmission5.utils.visible
import kotlinx.android.synthetic.main.fragment_fav_nextm.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class FavNextmFragment : Fragment() {
    private var itemFavorites: ArrayList<DataFavoriteEvent> = arrayListOf()
    private var adapter: FavoriteAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_nextm, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initial()
        swipe_next_m.isRefreshing = true
        swipe_next_m.setColorSchemeResources(R.color.colorAccent, R.color.colorTwitter, R.color.colorYoutube, R.color.colorFacebook)
        swipe_next_m.onRefresh { showFavorite() }
    }

    private fun initial() {
        rv_fav_nextm.layoutManager = LinearLayoutManager(context)
        adapter = FavoriteAdapter(context!!, itemFavorites)
        rv_fav_nextm.adapter = adapter
    }

    private fun showFavorite() {
        itemFavorites.clear()
        context?.databaseNext?.use {
            val result = select(DataFavoriteEvent.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<DataFavoriteEvent>())
            itemFavorites.addAll(favorite)
            adapter?.notifyDataSetChanged()
        }
        if (itemFavorites.size <= 0) enableNoData()
        disableLoad()
    }

    private fun disableLoad() {
        if (swipe_next_m.isRefreshing) {
            swipe_next_m.isRefreshing = false
        }
    }

    private fun enableNoData() {
        tv_fav_emptyn.visible()
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

}
