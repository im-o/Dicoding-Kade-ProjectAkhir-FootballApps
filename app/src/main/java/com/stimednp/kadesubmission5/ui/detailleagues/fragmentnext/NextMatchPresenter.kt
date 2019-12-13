package com.stimednp.kadesubmission5.ui.detailleagues.fragmentnext

import com.stimednp.kadesubmission5.model.events.DataNextMatch
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import com.stimednp.kadesubmission5.model.events.ResponseNextMatch
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentnext.INextRepositoryCallback
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentnext.NextRepository

/**
 * Created by rivaldy on 12/8/2019.
 */

class NextMatchPresenter(private val view: INextMatchView, private val nextRepository: NextRepository) : INextMatchPresenter {
    override fun getNextMatch(idLeague: String) {
        view.onShowLoading()
        nextRepository.getNextMatch(idLeague, object : INextRepositoryCallback<ResponseNextMatch> {
            override fun onDataLoaded(data: ArrayList<DataNextMatch>, itemsH: ArrayList<DataTeamsBadge>, itemsA: ArrayList<DataTeamsBadge>) {
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