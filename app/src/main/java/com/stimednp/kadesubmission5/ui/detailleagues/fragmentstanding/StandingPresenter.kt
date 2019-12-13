package com.stimednp.kadesubmission5.ui.detailleagues.fragmentstanding

import com.stimednp.kadesubmission5.model.standings.DataStandings
import com.stimednp.kadesubmission5.model.standings.ResponseStandings
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentstanding.IStandingsRepositoryCallback
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentstanding.StandingsRepository

/**
 * Created by rivaldy on 12/13/2019.
 */

class StandingPresenter(private val view: IStandingView, private val standingsRepository: StandingsRepository): IStandingPresenter {
    override fun getStandings(idLeague: String) {
        view.onShowLoading()
        standingsRepository.getData(idLeague, object : IStandingsRepositoryCallback<ResponseStandings>{
            override fun onDataLoaded(data: ArrayList<DataStandings>, badge: ArrayList<DataTeamsBadge>) {
                view.onDataLoaded(data, badge)
                view.onHideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.onHideLoading()
            }
        })
    }
}