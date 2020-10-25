package com.griyanet.aplikasigithubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class UserAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvUser: TextView = itemView.findViewById(R.id.tv_user)
        var tvFollower: TextView = itemView.findViewById(R.id.tv_follower)
        var tvFollowing: TextView = itemView.findViewById(R.id.tv_following)
        var imgUser: CircleImageView = itemView.findViewById(R.id.img_avatar)
        var tvFullname: TextView = itemView.findViewById(R.id.tv_fullname)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.tvUser.text = user.username
        holder.tvFullname.text = user.name
        holder.tvFollower.text = user.followers
        holder.tvFollowing.text = user.following
        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply(RequestOptions().override(70,70))
            .into(holder.imgUser)
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClick(users[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    interface OnItemClickCallback {
        fun onItemClick(data: User)
    }

}




