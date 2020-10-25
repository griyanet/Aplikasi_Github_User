package com.griyanet.aplikasigithubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val userDetail = intent.getParcelableExtra<User>(EXTRA_USER)

        userDetail?.apply {
            img_avatar.setImageResource(userDetail.avatar!!)
            tv_username.text = userDetail.username
            tv_name.text = userDetail.name
            tv_location.text = userDetail.location
            tv_company.text = userDetail.company
            tv_repository.text = userDetail.repository
            tv_follower.text = userDetail.followers
            tv_following.text = userDetail.following
        }
    }
}