package com.stimednp.kadesubmission5.ui.search

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.events.ResponseSearch
import com.stimednp.kadesubmission5.presenter.search.ISearchRepositoryCallback

/**
 * Created by rivaldy on 12/8/2019.
 */

interface ISearchView : ISearchRepositoryCallback<ResponseSearch>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
    fun showTextEmpty(text: String)
    fun hideTextEmpty()
}