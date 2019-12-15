package com.stimednp.kadesubmission5.model.db

/**
 * Created by rivaldy on 12/15/2019.
 */

class DataFavoriteTeam(
    val id: Long?,
    val idTeam: String?,
    val strTeam: String?,
    val strAlternate: String?,
    val strDescriptionEN: String?,
    val strTeamBadge: String?
) {
    companion object {
        const val TABLE_FAVTEAM: String = "TABLE_FAVTEAM"
        const val ID: String = "ID_"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_ALTERNATE: String = "STR_ALTERNATE"
        const val STR_DESC: String = "STR_DESC"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
    }
}