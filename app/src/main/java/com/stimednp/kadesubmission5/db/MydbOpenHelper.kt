package com.stimednp.kadesubmission5.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.ID
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.ID_EVENT
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.INT_SCOREA
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.INT_SCOREH
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_BADGEA
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_BADGEH
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_DATEEV
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_EVENT
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_LEAGUE
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_SPORT
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_TEAMA
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_TEAMH
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.STR_TIMEEV
import com.stimednp.kadesubmission5.model.db.DataFavoriteEvent.Companion.TABLE_FAVORITE
import com.stimednp.kadesubmission5.model.db.DataFavoriteTeam
import org.jetbrains.anko.db.*

/**
 * Created by rivaldy on 11/28/2019.
 */

object MydbOpenHelper {
    class MydbOpenHelperLast(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "favorite_lastevent.db", null, 1) {
        companion object {
            private var instance: MydbOpenHelperLast? = null
            @Synchronized
            fun getInstance(context: Context): MydbOpenHelperLast {
                if (instance == null) {
                    instance = MydbOpenHelperLast(context.applicationContext)
                }
                return instance as MydbOpenHelperLast
            }
        }

        override fun onCreate(db: SQLiteDatabase) { //create table
            createTableFavEvent(db)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) { //upgrade table
            db.dropTable(TABLE_FAVORITE, true)
        }
    }

    val Context.databaseLast: MydbOpenHelperLast //acces property context
        get() = MydbOpenHelperLast.getInstance(applicationContext)

    class MydbOpenHelperNext(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "favorite_nextevent.db", null, 1) {
        companion object {
            private var instance: MydbOpenHelperNext? = null
            @Synchronized
            fun getInstance(context: Context): MydbOpenHelperNext {
                if (instance == null) {
                    instance = MydbOpenHelperNext(context.applicationContext)
                }
                return instance as MydbOpenHelperNext
            }
        }

        override fun onCreate(db: SQLiteDatabase) {
            createTableFavEvent(db)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
            db.dropTable(TABLE_FAVORITE, true)
        }
    }

    val Context.databaseNext: MydbOpenHelperNext //acces property context
        get() = MydbOpenHelperNext.getInstance(applicationContext)

    class MydbOpenHelperTeam(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "favorite_teams.db", null, 1) {
        companion object {
            private var instance: MydbOpenHelperTeam? = null
            @Synchronized
            fun getInstance(context: Context): MydbOpenHelperTeam {
                if (instance == null) {
                    instance = MydbOpenHelperTeam(context.applicationContext)
                }
                return instance as MydbOpenHelperTeam
            }
        }

        override fun onCreate(db: SQLiteDatabase) {
            createTableFavTeam(db)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
            db.dropTable(DataFavoriteTeam.TABLE_FAVTEAM, true)
        }
    }

    val Context.databaseTeams: MydbOpenHelperTeam //acces property context
        get() = MydbOpenHelperTeam.getInstance(applicationContext)

    //create table favorite event
    private fun createTableFavEvent(db: SQLiteDatabase) {
        db.createTable(
            TABLE_FAVORITE, true,
            ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ID_EVENT to TEXT + UNIQUE,
            STR_DATEEV to TEXT,
            STR_TIMEEV to TEXT,
            STR_EVENT to TEXT,
            STR_SPORT to TEXT,
            STR_LEAGUE to TEXT,
            STR_TEAMH to TEXT,
            STR_TEAMA to TEXT,
            INT_SCOREH to INTEGER,
            INT_SCOREA to INTEGER,
            STR_BADGEH to TEXT,
            STR_BADGEA to TEXT
        )
    }

    //create table favorite teams
    private fun createTableFavTeam(db: SQLiteDatabase) {
        db.createTable(
            DataFavoriteTeam.TABLE_FAVTEAM, true,
            DataFavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DataFavoriteTeam.ID_TEAM to TEXT + UNIQUE,
            DataFavoriteTeam.STR_TEAM to TEXT,
            DataFavoriteTeam.STR_ALTERNATE to TEXT,
            DataFavoriteTeam.STR_DESC to TEXT,
            DataFavoriteTeam.STR_TEAM_BADGE to TEXT
        )
    }
}
