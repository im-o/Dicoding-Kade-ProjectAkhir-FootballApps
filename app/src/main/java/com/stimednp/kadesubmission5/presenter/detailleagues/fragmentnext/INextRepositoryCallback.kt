package com.stimednp.kadesubmission5.presenter.detailleagues.fragmentnext

import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge

/**
 * Created by rivaldy on 12/8/2019.
 */

interface INextRepositoryCallback<T> {
    fun onDataLoaded(data: ArrayList<DataEventsMatch>, itemsH: ArrayList<DataTeamsBadge>, itemsA: ArrayList<DataTeamsBadge>)
    fun onDataError()
}