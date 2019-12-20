package com.stimednp.kadesubmission5.ui.detailteams

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.stimednp.kadesubmission5.R
import com.stimednp.kadesubmission5.db.MydbOpenHelper.databaseTeams
import com.stimednp.kadesubmission5.model.db.DataFavoriteTeam
import com.stimednp.kadesubmission5.model.teams.DataTeams
import com.stimednp.kadesubmission5.presenter.detailteam.DTeamsRepository
import com.stimednp.kadesubmission5.utils.invisible
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.item_body_dteam.*
import kotlinx.android.synthetic.main.item_dhead_team.*
import kotlinx.android.synthetic.main.item_sosmed_left.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), IDTeamView {
    private lateinit var dTeamPresenter: DTeamPresenter
    private val nameSavePref = "my_savepref_favteam"
    private var keyIdSavePref = "my_key_default"
    private var menuItem: Menu? = null

    private lateinit var dataTeams: DataTeams

    companion object {
        const val EXTRA_IDTEAMS: String = "extra_idteams"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val idTeam = intent.getStringExtra(EXTRA_IDTEAMS) ?: ""
        keyIdSavePref = idTeam
        getDetailTeam(idTeam)
        setToolbar()
    }

    private fun getDetailTeam(idTeam: String?) {
        dTeamPresenter = DTeamPresenter(this, DTeamsRepository())
        dTeamPresenter.getListTeam(idTeam.toString())
    }

    private fun setToolbar() {
        setSupportActionBar(tbar_dteam)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
//        tbar_dteam.setTitle(R.string.app_detail_team)
        tbar_dteam.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp)
        tbar_dteam.setNavigationOnClickListener { finish() }
    }

    override fun showMsgSucces(text: String) {
        //need this for future
    }

    override fun showMsgFail(text: String) {
        //need this for future
    }

    override fun showTextEmpty(text: String) {
        //need this for future
    }

    override fun hideTextEmpty() {
        //need this for future
    }

    override fun onDataLoaded(data: DataTeams) {
        dataTeams = data
        setDataUI(data)
        addButtonListener(data)
    }

    override fun onDataError() {
        toast("Error or No Data")
    }

    override fun onShowLoading() {
        //need this for future
    }

    override fun onHideLoading() {
        //need this for future
    }

    private fun setDataUI(data: DataTeams) {
        val urlBadge = "${data.strTeamBadge}/preview"
        tv_dteam_name.text = data.strTeam
        tv_dalt_name.text = data.strAlternate
        tv_desc_team.text = data.strDescriptionEN
        Picasso.get().load(urlBadge).into(imgv_dbagde, object : Callback {
            override fun onSuccess() {
                progress_dteam.invisible()
            }

            override fun onError(e: java.lang.Exception?) {
                progress_dteam.invisible()
            }

        })
    }

    private fun addButtonListener(data: DataTeams) {
        tv_webt.setOnClickListener { goUri(data.strWebsite) }
        tv_fbt.setOnClickListener { goUri(data.strFacebook) }
        tv_igt.setOnClickListener { goUri(data.strInstagram) }
        tv_twitt.setOnClickListener { goUri(data.strTwitter) }
        tv_ytt.setOnClickListener { goUri(data.strYoutube) }
    }

    private fun goUri(url: String?) {
        if (url == "") {
            toast(getString(R.string.str_nourl))
        } else {
            try {
                val uri = Uri.parse("http://$url")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                Handler().postDelayed({ startActivity(intent) }, 100)
            } catch (e: Exception) {
                toast("Something Error uri : $url")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_addfav, menu)
        menuItem = menu
        changeIconFavorite(keyIdSavePref)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_tofav -> {
                checkMyPref(dataTeams.idTeam)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkMyPref(idTeam: String) {
        val isFavorite = checkPrefById(idTeam) // add/true data if false
        if (isFavorite) { //delete data
            setPrefById(idTeam, false)
            changeIconFavorite(idTeam)
            removeFavoriteTeam(dataTeams)
        } else { //insert data
            setPrefById(idTeam, true)
            changeIconFavorite(idTeam)
            addtoFavoriteTeam(dataTeams)
        }
    }

    private fun changeIconFavorite(idTeam: String) {
        val isFavorite = checkPrefById(idTeam)
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
        }
    }

    private fun checkPrefById(keyPref: String): Boolean {
        val mSharedPref = getSharedPreferences(nameSavePref, Context.MODE_PRIVATE)
        return mSharedPref.getBoolean(keyPref, false)
    }

    private fun setPrefById(keyIdSavePref: String, isFavorite: Boolean) {
        val mSharedPref = getSharedPreferences(nameSavePref, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = mSharedPref.edit()
        editor.putBoolean(keyIdSavePref, isFavorite)
        editor.apply()
    }

    private fun addtoFavoriteTeam(teams: DataTeams) {
        try {
            databaseTeams.use {
                insert(
                    DataFavoriteTeam.TABLE_FAVTEAM,
                    DataFavoriteTeam.ID_TEAM to teams.idTeam,
                    DataFavoriteTeam.STR_TEAM to teams.strTeam,
                    DataFavoriteTeam.STR_ALTERNATE to teams.strAlternate,
                    DataFavoriteTeam.STR_DESC to teams.strDescriptionEN,
                    DataFavoriteTeam.STR_TEAM_BADGE to teams.strTeamBadge
                )
            }
            toast("Succes added to favorites")
        } catch (er: SQLiteConstraintException) {
            toast("Failed to delete favorite! -> ${er.message}")
            Log.e("INIII", "ERRROR ${er.message}")
        }
    }

    private fun removeFavoriteTeam(teams: DataTeams) {
        val id: String = teams.idTeam
        try {
            databaseTeams.use {
                delete(
                    DataFavoriteTeam.TABLE_FAVTEAM,
                    "(${DataFavoriteTeam.ID_TEAM} = {id})",
                    "id" to id
                )
            }
            toast("Succes remove from favorite")
        } catch (er: SQLiteConstraintException) {
            toast("Failed to delete favorite! -> ${er.message}")
            Log.e("INIII", "ERRROR ${er.message}")
        }
    }

}
