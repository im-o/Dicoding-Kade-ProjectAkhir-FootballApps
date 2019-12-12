package com.stimednp.kadesubmission5.presenter.search

import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge

/**
 * Created by rivaldy on 12/8/2019.
 */

interface ISearchRepositoryCallback<T> {
    fun onDataLoaded(data: ArrayList<DataEventsMatch>, teamH: ArrayList<DataTeamsBadge>, teamA: ArrayList<DataTeamsBadge>)
    fun onDataError()
}