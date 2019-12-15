package com.stimednp.kadesubmission5.presenter.searchteam

import android.annotation.SuppressLint
import com.stimednp.kadesubmission5.api.ApiClient
import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by rivaldy on 12/14/2019.
 */

class SearchTRepository {
    private val tsdbService = ApiClient.iServiceTsdb
    fun getSearhTeam(strTeam: String?, callback: ISearchTRepositoryCallback<ResponseTeams>) {
        GlobalScope.launch(Dispatchers.Main) {
            val listTeams = tsdbService.getSearchTeam(strTeam)
            try {
                val result = listTeams.await()
                val resultBody = result.body()
                val teams = resultBody?.teams
                filterList(teams!!, callback)
            } catch (er: Exception) {
                callback.onDataError()
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun filterList(teams: ArrayList<DataTeams>, callback: ISearchTRepositoryCallback<ResponseTeams>) {
        val listTeams = ArrayList<DataTeams>()
        for (i in teams.indices) {
            val strSoccer = teams[i].strSport.toLowerCase()
            if (strSoccer == "soccer") listTeams.add(teams[i])
        }
        if (listTeams.size > 0) setDataSearch(listTeams, callback) else callback.onDataError()
    }

    private fun setDataSearch(teams: ArrayList<DataTeams>, callback: ISearchTRepositoryCallback<ResponseTeams>) {
        callback.onDataLoaded(teams)
    }
}