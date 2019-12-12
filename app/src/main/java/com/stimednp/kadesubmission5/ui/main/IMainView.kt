package com.stimednp.kadesubmission5.ui.main

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.leagues.ResponseLeagues
import com.stimednp.kadesubmission5.presenter.main.IMainRepositoryCallback

/**
 * Created by rivaldy on 12/8/2019.
 */

interface IMainView : IMainRepositoryCallback<ResponseLeagues>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
    fun showTextEmpty(text: String)
    fun hideTextEmpty()
}