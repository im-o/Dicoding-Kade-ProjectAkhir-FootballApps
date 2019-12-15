package com.stimednp.kadesubmission5.presenter.detailteam

import com.stimednp.kadesubmission5.model.teams.DataTeams

/**
 * Created by rivaldy on 12/14/2019.
 */

interface IDTeamsRepositoryCallback<T> {
    fun onDataLoaded(data: DataTeams)
    fun onDataError()
}