package com.stimednp.kadesubmission5.ui.detailleagues.fragmentstanding

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.standings.ResponseStandings
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentstanding.IStandingsRepositoryCallback

/**
 * Created by rivaldy on 12/13/2019.
 */

interface IStandingView : IStandingsRepositoryCallback<ResponseStandings>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
    fun showTextEmpty(text: String)
    fun hideTextEmpty()
}