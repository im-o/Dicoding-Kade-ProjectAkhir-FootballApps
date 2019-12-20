package com.stimednp.kadesubmission5.presenter.detailematch

import com.stimednp.kadesubmission5.api.ApiClient
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by rivaldy on 12/9/2019.
 */

class DetailsERepository {
    fun getDetailEvent(idEvent: String, callback: IDetailsERepositoryCallback<ResponseEvents>) {
        val tsdbService = ApiClient.iServiceTsdb
        GlobalScope.launch(Dispatchers.Main) {
            val listDetail = tsdbService.getDetailEvent(idEvent)
            try {
                EspressoIdlingResource.increment() //DELETE THIS TEST AFTER TESTING
                val response = listDetail.await()
                if (response.isSuccessful) {
                    val resbody = response.body()
                    if (resbody != null) callback.onDataLoaded(resbody.events[0])
                }
            } catch (er: Exception) {
                callback.onDataError()
            }
        }
    }
}