package com.stimednp.kadesubmission5.presenter.detailleagues.fragmentteam

import com.stimednp.kadesubmission5.api.ApiClient
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by rivaldy on 12/12/2019.
 */

class TeamRepository {
    private val tsdbService = ApiClient.iServiceTsdb
    fun getListTeam(id: String?, callback: ITeamRepositoryCallback<ResponseTeams>) {
        GlobalScope.launch(Dispatchers.Main) {
            val listTeams = tsdbService.getListTeam(id)
            try {
                val result = listTeams.await()
                val resultBody = result.body()
                val teams = resultBody?.teams
                callback.onDataLoaded(teams!!)
            } catch (er: Exception) {
                callback.onDataError()
            }
        }
    }
}