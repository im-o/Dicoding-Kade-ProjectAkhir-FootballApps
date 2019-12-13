package com.stimednp.kadesubmission5.presenter.detailleagues.fragmentstanding

import com.stimednp.kadesubmission5.model.standings.DataStandings
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge

/**
 * Created by rivaldy on 12/13/2019.
 */

interface IStandingsRepositoryCallback<T> {
    fun onDataLoaded(data: ArrayList<DataStandings>, badge: ArrayList<DataTeamsBadge>)
    fun onDataError()
}