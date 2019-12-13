package com.stimednp.kadesubmission5.presenter.detailleagues.fragmentteam

import com.stimednp.kadesubmission5.model.teams.DataTeams

/**
 * Created by rivaldy on 12/12/2019.
 */

interface ITeamRepositoryCallback<T> {
    fun onDataLoaded(data: ArrayList<DataTeams>)
    fun onDataError()
}