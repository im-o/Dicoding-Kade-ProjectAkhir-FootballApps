package com.stimednp.kadesubmission5.ui.main

import com.stimednp.kadesubmission5.model.leagues.DataLeagues
import com.stimednp.kadesubmission5.model.leagues.ResponseLeagues
import com.stimednp.kadesubmission5.presenter.main.IMainRepositoryCallback
import com.stimednp.kadesubmission5.presenter.main.MainRepository

/**
 * Created by rivaldy on 12/8/2019.
 */

class MainPresenter(private val view: IMainView, private val mainRepository: MainRepository) : IMainPresenter {
    override fun getLeaguesData() {
        view.onShowLoading()
        mainRepository.getIdListLeague(object : IMainRepositoryCallback<ResponseLeagues> {
            override fun onDataLoaded(data: ArrayList<DataLeagues>) {
                view.onDataLoaded(data)
                view.onHideLoading()
                if (data.size < 0) {
                    view.showTextEmpty("NOTHING LEAGUES DATA!")
                }
            }

            override fun onDataError() {
                view.onDataError()
                view.onHideLoading()
                view.showTextEmpty("SOMETHING ERROR!")
            }
        })
    }
}