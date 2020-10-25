package com.griyanet.aplikasigithubuser

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var rvUser: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUser = findViewById(R.id.rv_users)
        rvUser.setHasFixedSize(true)
        rvUser.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(getListUser())
        rvUser.adapter = adapter
        setListClickAction()
    }

    private fun setListClickAction() {
        adapter.setOnItemClickCallback(
            object : UserAdapter.OnItemClickCallback {
                override fun onItemClick(data: User) {
                    val clickIntent = Intent(this@MainActivity, UserDetail::class.java).apply {
                        putExtra(UserDetail.EXTRA_USER, data)
                    }
                    startActivity(clickIntent)
                }
            }
        )
    }

    private fun getListUser(): ArrayList<User> {
        val userName = resources.getStringArray(R.array.username)
        val noFollower = resources.getStringArray(R.array.followers)
        val noFollowing = resources.getStringArray(R.array.following)
        val userPhoto = resources.obtainTypedArray(R.array.avatar)
        val name = resources.getStringArray(R.array.name)
        val location = resources.getStringArray(R.array.location)
        val company = resources.getStringArray(R.array.company)
        val repository = resources.getStringArray(R.array.repository)

        val listUser = arrayListOf<User>()
        for (position in userName.indices) {
            val user = User()
            user.username = userName[position]
            user.followers = noFollower[position]
            user.following = noFollowing[position]
            user.avatar = userPhoto.getResourceId(position, -1)
            user.name = name[position]
            user.location = location[position]
            user.company = company[position]
            user.repository = repository[position]
            listUser.add(user)
        }
        userPhoto.recycle()
        return listUser
    }


}