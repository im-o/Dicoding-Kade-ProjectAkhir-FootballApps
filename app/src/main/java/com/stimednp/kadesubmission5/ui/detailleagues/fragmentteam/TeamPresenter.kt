package com.stimednp.kadesubmission5.ui.detailleagues.fragmentteam

import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentteam.ITeamRepositoryCallback
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentteam.TeamRepository

/**
 * Created by rivaldy on 12/12/2019.
 */

class TeamPresenter(private val view: ITeamView, private val teamRepository: TeamRepository) : ITeamPresenter {
    override fun getListTeam(id: String) {
        view.onShowLoading()
        teamRepository.getListTeam(id, object : ITeamRepositoryCallback<ResponseTeams> {
            override fun onDataLoaded(data: ArrayList<DataTeams>) {
                view.onDataLoaded(data)
                view.onHideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.onHideLoading()
            }

        })
    }
}