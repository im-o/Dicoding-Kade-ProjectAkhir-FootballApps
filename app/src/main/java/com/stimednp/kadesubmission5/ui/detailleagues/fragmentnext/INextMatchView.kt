package com.stimednp.kadesubmission5.ui.detailleagues.fragmentnext

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentnext.INextRepositoryCallback

/**
 * Created by rivaldy on 12/8/2019.
 */

interface INextMatchView : INextRepositoryCallback<ResponseEvents>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
    fun showTextEmpty(text: String)
    fun hideTextEmpty()
}