package com.stimednp.kadesubmission5.ui.detailevents

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.presenter.detailematch.IDetailsERepositoryCallback

/**
 * Created by rivaldy on 12/9/2019.
 */

interface IDetailsEView : IDetailsERepositoryCallback<ResponseEvents>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
}