package com.stimednp.kadesubmission5.ui.searchteam

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import com.stimednp.kadesubmission5.presenter.searchteam.ISearchTRepositoryCallback

/**
 * Created by rivaldy on 12/14/2019.
 */

interface ISearchTView : ISearchTRepositoryCallback<ResponseTeams>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
    fun showTextEmpty(text: String)
    fun hideTextEmpty()
}