package com.stimednp.kadesubmission5.ui.detailteams

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.presenter.detailteam.IDTeamsRepositoryCallback

/**
 * Created by rivaldy on 12/14/2019.
 */

interface IDTeamView : IDTeamsRepositoryCallback<DataTeams>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
    fun showTextEmpty(text: String)
    fun hideTextEmpty()
}