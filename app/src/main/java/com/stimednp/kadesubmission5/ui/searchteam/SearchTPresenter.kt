package com.stimednp.kadesubmission5.ui.searchteam

import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import com.stimednp.kadesubmission5.presenter.searchteam.ISearchTRepositoryCallback
import com.stimednp.kadesubmission5.presenter.searchteam.SearchTRepository

/**
 * Created by rivaldy on 12/14/2019.
 */

class SearchTPresenter(private val view: ISearchTView, private val searchTRepository: SearchTRepository) : ISearchTPresenter {
    override fun getSearchTeam(strTeam: String) {
        view.onShowLoading()
        searchTRepository.getSearhTeam(strTeam, object : ISearchTRepositoryCallback<ResponseTeams> {
            override fun onDataLoaded(data: ArrayList<DataTeams>) {
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