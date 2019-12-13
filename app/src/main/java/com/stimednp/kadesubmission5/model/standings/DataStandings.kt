package com.stimednp.kadesubmission5.model.standings

data class DataStandings(
    val draw: Int,
    val goalsagainst: Int,
    val goalsdifference: Int,
    val goalsfor: Int,
    val loss: Int,
    val name: String,
    val played: Int,
    val teamid: String,
    val total: Int,
    val win: Int
)