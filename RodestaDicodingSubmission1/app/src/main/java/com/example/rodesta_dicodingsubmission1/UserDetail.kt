package com.example.rodesta_dicodingsubmission1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.user_detail.*

class UserDetail : AppCompatActivity() {
    companion object{
        var EXTRA_DATA = "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_detail)

        val listUser : UserData = intent.getParcelableExtra(EXTRA_DATA)
        val data_username = listUser.username
        val data_location = listUser.location
        val data_repository = listUser.repository
        val data_company = listUser.company
        val data_followers = listUser.followers
        val data_following = listUser.following

        detail_avatar.setImageResource(listUser.avatar!!)
        detail_username.text = "Username : $data_username"
        detail_name.text = listUser.name
        detail_location.text = "Location : $data_location"
        detail_repository.text = "Repository : $data_repository"
        detail_company.text = "Company : $data_company"
        detail_followers.text = "Followers : $data_followers"
        detail_following.text = "Following : $data_following"

    }

}