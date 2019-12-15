package com.stimednp.kadesubmission5.api

import com.stimednp.kadesubmission5.model.events.ResponseEvents
import com.stimednp.kadesubmission5.model.events.ResponseNextMatch
import com.stimednp.kadesubmission5.model.events.ResponseSearch
import com.stimednp.kadesubmission5.model.leagues.ResponseLeagues
import com.stimednp.kadesubmission5.model.standings.ResponseStandings
import com.stimednp.kadesubmission5.model.teams.ResponseTeams
import com.stimednp.kadesubmission5.model.teams.ResponseTeamsBadge
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by rivaldy on 11/10/2019.
 */

interface IServiceTsdb {
    //list liga
    @GET("api/v1/json/1/all_leagues.php")
    fun getListLeagues(): Deferred<Response<ResponseLeagues>>

    //detail liga
    @GET("api/v1/json/1/lookupleague.php")
    fun getDetailById(@Query("id") idLiga: String?): Deferred<Response<ResponseLeagues>>

    //next match
    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?): Deferred<Response<ResponseNextMatch>>

    //previous match
    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String?): Deferred<Response<ResponseEvents>>

    //search event / pertandingan
    @GET("api/v1/json/1/searchevents.php")
    fun getSearchEvent(@Query("e") e: String?): Deferred<Response<ResponseSearch>>

    //detail team
    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamBadge(@Query("id") idTeam: String?): Deferred<Response<ResponseTeamsBadge>>

    //detailEvent
    @GET("api/v1/json/1/lookupevent.php")
    fun getDetailEvent(@Query("id") idEvent: String?): Deferred<Response<ResponseEvents>>

    //list team
    @GET("api/v1/json/1/lookup_all_teams.php")
    fun getListTeam(@Query("id") idLeague: String?): Deferred<Response<ResponseTeams>>

    //detail team
    @GET("api/v1/json/1/lookupteam.php")
    fun getDetailTeam(@Query("id") idTeam: String?): Deferred<Response<ResponseTeams>>

    //search team
    @GET("api/v1/json/1/searchteams.php")
    fun getSearchTeam(@Query("t") strTeam: String?): Deferred<Response<ResponseTeams>>

    //get standing
    @GET("api/v1/json/1/lookuptable.php")
    fun getStanding(@Query("l") idLeague: String?): Deferred<Response<ResponseStandings>>
}