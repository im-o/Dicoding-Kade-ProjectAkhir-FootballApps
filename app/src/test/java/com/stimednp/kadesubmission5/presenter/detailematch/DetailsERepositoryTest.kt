package com.stimednp.kadesubmission5.presenter.detailematch

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.ui.detailevents.DetailsEPresenter
import com.stimednp.kadesubmission5.ui.detailevents.IDetailsEView
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by rivaldy on 12/10/2019.
 */

class DetailsERepositoryTest {
    @Mock
    lateinit var view: IDetailsEView
    @Mock
    lateinit var detailsERepository: DetailsERepository
    @Mock
    lateinit var dataEventsMatch: DataEventsMatch

    private lateinit var detailsEPresenter: DetailsEPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailsEPresenter = DetailsEPresenter(view, detailsERepository)
    }

    @After
    fun tearDown() {
        print("Finish testing")
    }

    @Test
    fun getDetailEventSucces() {
        val idEvent = "441613"
        detailsEPresenter.getEventsDetail(idEvent)
        argumentCaptor<IDetailsERepositoryCallback<ResponseEvents>>().apply {
            verify(detailsERepository).getDetailEvent(eq(idEvent), capture())
            firstValue.onDataLoaded(dataEventsMatch)
        }
        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onHideLoading()
        Mockito.verify(view).onDataLoaded(dataEventsMatch)
    }

    @Test
    fun getDetailEventError() {
        val idEvent = ""
        detailsEPresenter.getEventsDetail(idEvent)
        argumentCaptor<IDetailsERepositoryCallback<ResponseEvents>>().apply {
            verify(detailsERepository).getDetailEvent(eq(idEvent), capture())
            firstValue.onDataError()
        }
        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onHideLoading()
        Mockito.verify(view).onDataError()
    }
}