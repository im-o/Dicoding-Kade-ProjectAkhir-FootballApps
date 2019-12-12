package com.stimednp.kadesubmission5.presenter.detailleagues.fragmentlast

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.stimednp.kadesubmission5.model.events.DataEventsMatch
import com.stimednp.kadesubmission5.model.teams.DataTeamsBadge
import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.ui.detailleagues.fragementlast.ILastMatchView
import com.stimednp.kadesubmission5.ui.detailleagues.fragementlast.LastMatchPreseter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by rivaldy on 12/10/2019.
 */

class LastRepositoryTest {
    @Mock
    lateinit var view: ILastMatchView
    @Mock
    lateinit var lastRepository: LastRepository
    @Mock
    lateinit var dataEventLeagues: ArrayList<DataEventsMatch>
    @Mock
    lateinit var itemsH: ArrayList<DataTeamsBadge>
    @Mock
    lateinit var itemsA: ArrayList<DataTeamsBadge>

    private lateinit var lastMatchPreseter: LastMatchPreseter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lastMatchPreseter = LastMatchPreseter(view, lastRepository)
    }

    @After
    fun tearDown() {
        print("Finish testing")
    }

    @Test
    fun getLastMatchSuccess() {
        val idLeague = "4328"
        lastMatchPreseter.getLastMatch(idLeague)
        argumentCaptor<ILastRepositoryCallback<ResponseEvents>>().apply {
            verify(lastRepository).getLastMatch(eq(idLeague), capture())
            firstValue.onDataLoaded(dataEventLeagues, itemsH, itemsA)
        }
        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onHideLoading()
        Mockito.verify(view).hideTextEmpty()
        Mockito.verify(view).onDataLoaded(dataEventLeagues, itemsH, itemsA)
    }

    @Test
    fun getLastMatchError() {
        val idLeague = ""
        lastMatchPreseter.getLastMatch(idLeague)
        argumentCaptor<ILastRepositoryCallback<ResponseEvents>>().apply {
            verify(lastRepository).getLastMatch(eq(idLeague), capture())
            firstValue.onDataError()
        }
        Mockito.verify(view).onShowLoading()
        Mockito.verify(view).onDataError()
        Mockito.verify(view).onHideLoading()
    }
}