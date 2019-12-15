package com.stimednp.kadesubmission5.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.model.standings.DataStandings
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import kotlinx.android.synthetic.main.item_standings.view.*

/**
 * Created by rivaldy on 12/13/2019.
 */

class StandingAdapter(private val dataStandings: ArrayList<DataStandings>, private val badges: ArrayList<DataTeamsBadge>) : RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        return StandingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_standings, parent, false))
    }

    override fun getItemCount() = dataStandings.size

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        holder.bindItem(dataStandings[position], badges[position], position)
    }

    class StandingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(data: DataStandings, badge: DataTeamsBadge, num: Int) {
            val urlBadge = "${badge.strTeamBadge}/preview"
            view.tv_number.text = (num + 1).toString()
            view.tv_teamname.text = data.name
            view.tv_mp.text = data.played.toString()
            view.tv_w.text = data.win.toString()
            view.tv_d.text = data.draw.toString()
            view.tv_l.text = data.loss.toString()
            view.tv_gf.text = data.goalsfor.toString()
            view.tv_ga.text = data.goalsagainst.toString()
            view.tv_gd.text = data.goalsdifference.toString()
            view.tv_pts.text = data.total.toString()
            Picasso.get().load(urlBadge).into(view.imgv_bagde)
        }
    }
}