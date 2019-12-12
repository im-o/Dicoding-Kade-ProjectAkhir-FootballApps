package com.stimednp.kadesubmission5.presenter.detailleagues.fragmentnext

import com.stimednp.kadesubmission5.api.ApiClient
import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by rivaldy on 12/8/2019.
 */

class NextRepository {
    private val tsdbService = ApiClient.iServiceTsdb
    fun getNextMatch(idLeague: String, callback: INextRepositoryCallback<ResponseEvents>) {
        GlobalScope.launch(Dispatchers.Main) {
            val listIdEvent = tsdbService.getNextMatch(idLeague)
            try {
                EspressoIdlingResource.increment() //DELETE THIS TEST AFTER TESTING
                val result = listIdEvent.await()
                val resultBody = result.body()
                val list = resultBody?.events
                savetoArrays(list!!, callback)
            } catch (err: Exception) {
                callback.onDataError()
            }
        }
    }

    private fun savetoArrays(events: ArrayList<DataEventsMatch>, callback: INextRepositoryCallback<ResponseEvents>) {
        val badgeH = ArrayList<Int>()
        val badgeA = ArrayList<Int>()

        for (i in events.indices) {
            val teamH = events[i].idHomeTeam
            val teamA = events[i].idAwayTeam

            badgeH.add(teamH!!)
            badgeA.add(teamA!!)
        }
        setIdTeam(events, badgeH, badgeA, callback)
    }

    private fun setIdTeam(events: ArrayList<DataEventsMatch>, teamH: ArrayList<Int>, teamA: ArrayList<Int>, callback: INextRepositoryCallback<ResponseEvents>) {
        val tsdbService = ApiClient.iServiceTsdb
        GlobalScope.launch(Dispatchers.Main) {
            val itemsH = ArrayList<DataTeamsBadge>()
            val itemsA = ArrayList<DataTeamsBadge>()
            for (i in events.indices) {
                val listIdHome = tsdbService.getDetailTeamH(teamH[i])
                val listIdAway = tsdbService.getDetailTeamA(teamA[i])
                try {
                    val responseH = listIdHome.await()
                    val bodyH = responseH.body()
                    val responseA = listIdAway.await()
                    val bodyA = responseA.body()
                    itemsH.addAll(bodyH?.teams!!)
                    itemsA.addAll(bodyA?.teams!!)
                } catch (e: Exception) {
                    callback.onDataError()
                }
            }
            setAdapter(events, itemsH, itemsA, callback)
        }
    }

    private fun setAdapter(itemsE: ArrayList<DataEventsMatch>, itemsH: ArrayList<DataTeamsBadge>, itemsA: ArrayList<DataTeamsBadge>, callback: INextRepositoryCallback<ResponseEvents>) {
        callback.onDataLoaded(itemsE, itemsH, itemsA)
    }
}