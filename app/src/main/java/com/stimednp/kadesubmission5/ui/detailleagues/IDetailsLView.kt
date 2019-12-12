package com.stimednp.kadesubmission5.ui.detailleagues

import com.stimednp.kadesubmission5.base.IView
import com.stimednp.kadesubmission5.model.leagues.ResponseLeagues
import com.stimednp.kadesubmission5.presenter.detailleagues.IDetailsLRepositoryCallback

/**
 * Created by rivaldy on 12/8/2019.
 */

interface IDetailsLView : IDetailsLRepositoryCallback<ResponseLeagues>, IView {
    fun showMsgSucces(text: String)
    fun showMsgFail(text: String)
}