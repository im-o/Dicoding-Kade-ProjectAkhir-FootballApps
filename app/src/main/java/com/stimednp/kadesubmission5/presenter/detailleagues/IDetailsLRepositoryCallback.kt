package com.stimednp.kadesubmission5.presenter.detailleagues

import com.stimednp.kadesubmission5.model.leagues.DataLeagues

/**
 * Created by rivaldy on 12/8/2019.
 */

interface IDetailsLRepositoryCallback<T> {
    fun onDataLoaded(data: DataLeagues)
    fun onDataError()
}