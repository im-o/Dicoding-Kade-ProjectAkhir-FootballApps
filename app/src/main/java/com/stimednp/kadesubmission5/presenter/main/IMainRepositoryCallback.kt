package com.stimednp.kadesubmission5.presenter.main

import com.stimednp.kadesubmission5.model.leagues.DataLeagues

/**
 * Created by rivaldy on 12/8/2019.
 */

interface IMainRepositoryCallback<T> {
    fun onDataLoaded(data: ArrayList<DataLeagues>)
    fun onDataError()
}