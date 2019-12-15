package com.stimednp.kadesubmission5.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.ui.detailteams.DetailTeamActivity
import kotlinx.android.synthetic.main.item_team_list.view.*
import org.jetbrains.anko.startActivity

/**
 * Created by rivaldy on 12/13/2019.
 */

class TeamsAdapter(private val context: Context?, private val teams: ArrayList<DataTeams>) : RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team_list, parent, false))
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(teams[position])
        holder.itemView.setOnClickListener {
            context?.startActivity<DetailTeamActivity>(DetailTeamActivity.EXTRA_IDTEAMS to teams[position].idTeam)
        }
    }

    class TeamsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(data: DataTeams) {
            val urlbadge = "${data.strTeamBadge}/preview"
            view.tv_teamname.text = data.strTeam
            view.tv_teamalter.text = data.strAlternate
            view.tv_teamdesc.text = data.strDescriptionEN
            Picasso.get().load(urlbadge).into(view.imgv_teambadge)
        }
    }
}