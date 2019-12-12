package com.stimednp.kadesubmission5.ui.detailleagues

import com.stimednp.kadesubmission5.model.leagues.DataLeagues
import com.stimednp.kadesubmission5.model.leagues.ResponseLeagues
import com.stimednp.kadesubmission5.presenter.detailleagues.DetailsLRepository
import com.stimednp.kadesubmission5.presenter.detailleagues.IDetailsLRepositoryCallback

/**
 * Created by rivaldy on 12/8/2019.
 */

class DetailsPresenter(private val view: IDetailsLView, private val detailsLRepository: DetailsLRepository) : IDetailsLPresenter {
    override fun getLeaguesDetail(id: String?) {
        view.onShowLoading()
        detailsLRepository.getDataById(id, object : IDetailsLRepositoryCallback<ResponseLeagues> {
            override fun onDataLoaded(data: DataLeagues) {
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