package com.stimednp.kadesubmission5.ui.detailleagues.fragementlast

import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentlast.ILastRepositoryCallback
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentlast.LastRepository

/**
 * Created by rivaldy on 12/8/2019.
 */

class LastMatchPreseter(private val view: ILastMatchView, private val lastRepository: LastRepository) : ILastMatchPreseter {
    override fun getLastMatch(idLeague: String) {
        view.onShowLoading()
        lastRepository.getLastMatch(idLeague, object : ILastRepositoryCallback<ResponseEvents> {
            override fun onDataLoaded(data: ArrayList<DataEventsMatch>, itemsH: ArrayList<DataTeamsBadge>, itemsA: ArrayList<DataTeamsBadge>) {
                view.onDataLoaded(data, itemsH, itemsA)
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