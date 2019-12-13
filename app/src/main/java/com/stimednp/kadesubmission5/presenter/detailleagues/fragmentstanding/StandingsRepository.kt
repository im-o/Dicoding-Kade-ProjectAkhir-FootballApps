package com.stimednp.kadesubmission5.presenter.detailleagues.fragmentstanding

import com.stimednp.kadesubmission5.api.ApiClient
import com.stimednp.kadesubmission5.model.standings.DataStandings
import com.stimednp.kadesubmission5.model.standings.ResponseStandings
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by rivaldy on 12/13/2019.
 */

class StandingsRepository {
    val tsdbservice = ApiClient.iServiceTsdb
    fun getData(idLeague: String, callback: IStandingsRepositoryCallback<ResponseStandings>) {
        GlobalScope.launch(Dispatchers.IO) {
            val listTeams = tsdbservice.getStanding(idLeague)
            try {
                val result = listTeams.await()
                val resultBody = result.body()
                val tables = resultBody?.table
                saveToArray(tables!!, callback)
            } catch (er: Exception) {
                callback.onDataError()
            }
        }
    }

    private fun saveToArray(tables: ArrayList<DataStandings>, callback: IStandingsRepositoryCallback<ResponseStandings>) {
        val idTeams = ArrayList<String>()
        for (i in tables.indices) {
            val id = tables[i].teamid
            idTeams.add(id)
        }
        getBadges(tables, idTeams, callback)
    }

    private fun getBadges(tables: ArrayList<DataStandings>, idTeams: ArrayList<String>, callback: IStandingsRepositoryCallback<ResponseStandings>) {
        GlobalScope.launch(Dispatchers.Main) {
            val listBadge = ArrayList<DataTeamsBadge>()
            for (i in idTeams.indices) {
                val teamBadge = tsdbservice.getTeamBadge(idTeams[i])
                try {
                    val result = teamBadge.await()
                    val resultBody = result.body()
                    listBadge.addAll(resultBody?.teams!!)
                } catch (er: Exception) {
                    callback.onDataError()
                }
            }
            setStandings(tables, listBadge, callback)
        }
    }

    private fun setStandings(tables: ArrayList<DataStandings>, listBadge: ArrayList<DataTeamsBadge>, callback: IStandingsRepositoryCallback<ResponseStandings>) {
        callback.onDataLoaded(tables, listBadge)
    }
}