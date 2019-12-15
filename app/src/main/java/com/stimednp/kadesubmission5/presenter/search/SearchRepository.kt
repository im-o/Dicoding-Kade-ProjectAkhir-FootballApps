package com.stimednp.kadesubmission5.presenter.search

import android.annotation.SuppressLint
import com.stimednp.kadesubmission5.api.ApiClient
import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.events.ResponseSearch
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import com.stimednp.kadesubmission5.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by rivaldy on 12/8/2019.
 */

class SearchRepository {
    private val tsdbService = ApiClient.iServiceTsdb
    fun getSearchData(text: String, callback: ISearchRepositoryCallback<ResponseSearch>) {
        GlobalScope.launch(Dispatchers.Main) {
            val listEvents = tsdbService.getSearchEvent(text)
            try {
                EspressoIdlingResource.increment() //DELETE THIS TEST AFTER TESTING
                val responseE = listEvents.await()
                val resBodyE = responseE.body()
                val event = resBodyE?.event
                savetoArrays(event, callback)
            } catch (er: Exception) {
                callback.onDataError()
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun savetoArrays(events: ArrayList<DataEventsMatch>?, callback: ISearchRepositoryCallback<ResponseSearch>) {
        val eventItems = ArrayList<DataEventsMatch>()
        val badgeH = ArrayList<String>()
        val badgeA = ArrayList<String>()

        for (i in events?.indices!!) {
            val idH = events[i].idHomeTeam
            val idA = events[i].idAwayTeam
            val ev = events[i]
            val sportSoccer = events[i].strSport?.toLowerCase()

            if (sportSoccer == "soccer") {
                badgeH.add(idH.toString())
                badgeA.add(idA.toString())
                eventItems.addAll(listOf(ev))
            }
        }
        if (eventItems.size > 0) setIdTeam(eventItems, badgeH, badgeA, callback) else callback.onDataError()
    }

    private fun setIdTeam(events: ArrayList<DataEventsMatch>, teamH: ArrayList<String>, teamA: ArrayList<String>, callback: ISearchRepositoryCallback<ResponseSearch>) {
        GlobalScope.launch(Dispatchers.Main) {
            val itemsH = ArrayList<DataTeamsBadge>()
            val itemsA = ArrayList<DataTeamsBadge>()
            if (events.size > 0) {
                for (i in events.indices) {
                    try {
                        val listIdHome = tsdbService.getTeamBadge(teamH[i])
                        val listIdAway = tsdbService.getTeamBadge(teamA[i])
                        val responseH = listIdHome.await()
                        val bodyH = responseH.body()
                        val responseA = listIdAway.await()
                        val bodyA = responseA.body()
                        itemsH.addAll(bodyH?.teams!!)
                        itemsA.addAll(bodyA?.teams!!)
                    } catch (er: Exception) {
                        callback.onDataError()
                    }
                }
                callback.onDataLoaded(events, itemsH, itemsA)
            } else {
                callback.onDataError()
            }
        }
    }
}