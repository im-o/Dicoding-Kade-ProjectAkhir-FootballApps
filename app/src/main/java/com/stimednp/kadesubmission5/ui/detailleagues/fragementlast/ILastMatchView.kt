package com.stimednp.kadesubmission5.ui.detailleagues.fragementlast

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.presenter.detailleagues.fragmentlast.ILastRepositoryCallback

/**
 * Created by rivaldy on 12/8/2019.
 */

interface ILastMatchView : ILastRepositoryCallback<ResponseEvents>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
    fun showTextEmpty(text: String)
    fun hideTextEmpty()
}