package com.stimednp.kadesubmission5.presenter.detailteam

import com.stimednp.kadesubmission5.api.ApiClient
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by rivaldy on 12/14/2019.
 */

class DTeamsRepository {
    private val tsdbService = ApiClient.iServiceTsdb
    fun getListTeam(id: String?, callback: IDTeamsRepositoryCallback<ResponseTeams>) {
        GlobalScope.launch(Dispatchers.Main) {
            val listTeams = tsdbService.getDetailTeam(id)
            try {
                val result = listTeams.await()
                val resultBody = result.body()
                val teams = resultBody?.teams
                callback.onDataLoaded(teams!![0])
            } catch (er: Exception) {
                callback.onDataError()
            }
        }
    }
}