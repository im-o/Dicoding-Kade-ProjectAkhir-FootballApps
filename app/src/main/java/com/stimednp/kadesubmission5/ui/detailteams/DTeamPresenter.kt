package com.stimednp.kadesubmission5.ui.detailteams

import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import com.stimednp.kadesubmission5.presenter.detailteam.DTeamsRepository
import com.stimednp.kadesubmission5.presenter.detailteam.IDTeamsRepositoryCallback

/**
 * Created by rivaldy on 12/14/2019.
 */

class DTeamPresenter(private val view: IDTeamView, private val dTeamsRepository: DTeamsRepository) : IDTeamPresenter {
    override fun getListTeam(idTeam: String) {
        view.onShowLoading()
        dTeamsRepository.getListTeam(idTeam, object : IDTeamsRepositoryCallback<ResponseTeams> {
            override fun onDataLoaded(data: DataTeams) {
                view.onDataLoaded(data)
                view.onHideLoading()
                view.hideTextEmpty()
            }

            override fun onDataError() {
                view.onDataError()
                view.onHideLoading()
            }

        })
    }
}