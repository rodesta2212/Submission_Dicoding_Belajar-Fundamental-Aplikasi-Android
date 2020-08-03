package com.example.rodesta_dicodingsubmission3

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rodesta_dicodingsubmission3.adapter.SectionsPagerAdapter
import com.example.rodesta_dicodingsubmission3.data.Favorite
import com.example.rodesta_dicodingsubmission3.data.UserData
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.AVATAR
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.COMPANY
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.CONTENT_URI
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.FAVORITE
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.LOCATION
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.NAME
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.REPOSITORY
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.USERNAME
import com.example.rodesta_dicodingsubmission3.db.FavoriteHelper
import kotlinx.android.synthetic.main.user_detail.*

class UserDetail : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_FAV = "extra_data"
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
    }

    private var isFavorite = false
    private lateinit var gitHelper: FavoriteHelper
    private var favorites: Favorite? = null
    private lateinit var imageAvatar: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail)

        gitHelper = FavoriteHelper.getInstance(applicationContext)
        gitHelper.open()

        favorites = intent.getParcelableExtra(EXTRA_NOTE)
        if (favorites != null) {
            setDataObject()
            isFavorite = true
            val checked: Int = R.drawable.ic_favorite
            btn_favorite.setImageResource(checked)
        } else {
            setData()
        }

        viewPagerConfig()
        btn_favorite.setOnClickListener(this)
    }

    private fun viewPagerConfig() {
        val viewPagerDetailAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = viewPagerDetailAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }

    @SuppressLint("SetTextI18n", "StringFormatInvalid")
    private fun setData() {
        val dataUser = intent.getParcelableExtra(EXTRA_DATA) as UserData
        dataUser.name?.let { setActionBarTitle(it) }
        detail_name.text = dataUser.name
        detail_username.text = dataUser.username
        detail_company.text = getString(R.string.company, dataUser.company)
        detail_location.text = getString(R.string.location, dataUser.location)
        detail_repository.text = getString(R.string.repository, dataUser.repository)
        Glide.with(this)
            .load(dataUser.avatar)
            .into(detail_avatar)
        imageAvatar = dataUser.avatar.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setDataObject() {
        val favoriteUser = intent.getParcelableExtra(EXTRA_NOTE) as Favorite
        favoriteUser.name?.let { setActionBarTitle(it) }
        detail_name.text = favoriteUser.name
        detail_username.text = favoriteUser.username
        detail_company.text = favoriteUser.company
        detail_location.text = favoriteUser.location
        detail_repository.text = favoriteUser.repository
        Glide.with(this)
            .load(favoriteUser.avatar)
            .into(detail_avatar)
        imageAvatar = favoriteUser.avatar.toString()
    }

    override fun onClick(view: View) {
        val checked: Int = R.drawable.ic_favorite
        val unChecked: Int = R.drawable.ic_favorite_border
        if (view.id == R.id.btn_favorite) {
            if (isFavorite) {
                gitHelper.deleteById(favorites?.username.toString())
                Toast.makeText(this, getString(R.string.delete_favorite), Toast.LENGTH_SHORT).show()
                btn_favorite.setImageResource(unChecked)
                isFavorite = false
            } else {
                val dataUsername = detail_username.text.toString()
                val dataName = detail_name.text.toString()
                val dataAvatar = imageAvatar
                val datacompany = detail_company.text.toString()
                val dataLocation = detail_location.text.toString()
                val dataRepository = detail_repository.text.toString()
                val dataFavorite = "1"

                val values = ContentValues()
                values.put(USERNAME, dataUsername)
                values.put(NAME, dataName)
                values.put(AVATAR, dataAvatar)
                values.put(COMPANY, datacompany)
                values.put(LOCATION, dataLocation)
                values.put(REPOSITORY, dataRepository)
                values.put(FAVORITE, dataFavorite)

                isFavorite = true
                contentResolver.insert(CONTENT_URI, values)
                Toast.makeText(this, getString(R.string.add_favorite), Toast.LENGTH_SHORT).show()
                btn_favorite.setImageResource(checked)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        gitHelper.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_change_settings -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
            R.id.action_change_notification -> {
                val mIntent = Intent(this, NotificationSettings::class.java)
                startActivity(mIntent)
            }
            R.id.action_favorite -> {
                val mIntent = Intent(this, UserFavorite::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
