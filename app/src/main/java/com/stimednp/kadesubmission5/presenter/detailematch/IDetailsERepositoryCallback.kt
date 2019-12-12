package com.stimednp.kadesubmission5.presenter.detailematch

import com.stimednp.kadesubmission5.model.events.DataEventsMatch

/**
 * Created by rivaldy on 12/9/2019.
 */

interface IDetailsERepositoryCallback<T> {
    fun onDataLoaded(data: DataEventsMatch)
    fun onDataError()
}