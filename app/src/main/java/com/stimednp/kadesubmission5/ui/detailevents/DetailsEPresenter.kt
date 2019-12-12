package com.stimednp.kadesubmission5.ui.detailevents

import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.presenter.detailematch.DetailsERepository
import com.stimednp.kadesubmission5.presenter.detailematch.IDetailsERepositoryCallback

/**
 * Created by rivaldy on 12/9/2019.
 */

class DetailsEPresenter(private val view: IDetailsEView, private val detailsERepository: DetailsERepository) : IDetailsEPresenter {
    override fun getEventsDetail(id: String?) {
        view.onShowLoading()
        detailsERepository.getDetailEvent(id.toString(), object : IDetailsERepositoryCallback<ResponseEvents> {
            override fun onDataLoaded(data: DataEventsMatch) {
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