package com.stimednp.kadesubmission5.ui.detailleagues.fragmentteam

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentteam.ITeamRepositoryCallback

/**
 * Created by rivaldy on 12/12/2019.
 */

interface ITeamView : ITeamRepositoryCallback<ResponseTeams>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
    fun showTextEmpty(text: String)
    fun hideTextEmpty()
}