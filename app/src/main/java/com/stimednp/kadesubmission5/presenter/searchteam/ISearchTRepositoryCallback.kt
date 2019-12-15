package com.stimednp.kadesubmission5.presenter.searchteam

import com.stimednp.kadesubmission5.model.teams.DataTeams

/**
 * Created by rivaldy on 12/14/2019.
 */

interface ISearchTRepositoryCallback<T> {
    fun onDataLoaded(data: ArrayList<DataTeams>)
    fun onDataError()
}