package com.stimednp.kadesubmission5.presenter.detailleagues.fragmentlast

import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge

/**
 * Created by rivaldy on 12/8/2019.
 */

interface ILastRepositoryCallback<T> {
    fun onDataLoaded(data: ArrayList<DataEventsMatch>, itemsH: ArrayList<DataTeamsBadge>, itemsA: ArrayList<DataTeamsBadge>)
    fun onDataError()
}