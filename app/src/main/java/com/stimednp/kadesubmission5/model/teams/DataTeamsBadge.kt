package com.stimednp.kadesubmission5.model.teams

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by rivaldy on 12/8/2019.
 */


@Parcelize
data class DataTeamsBadge(
    var idTeam: Int?,
    var strTeamBadge: String?
) : Parcelable