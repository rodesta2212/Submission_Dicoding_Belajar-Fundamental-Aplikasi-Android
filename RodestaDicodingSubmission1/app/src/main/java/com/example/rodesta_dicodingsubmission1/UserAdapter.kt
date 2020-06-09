package com.example.rodesta_dicodingsubmission1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private val context: Context, private val listUser : ArrayList<UserData>) : BaseAdapter() {

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if(itemView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.user_item, null, true)
        }
        val viewHolder = ViewHolder(itemView as View)
        val user = getItem(position) as UserData
        viewHolder.bind(user)
        return itemView
    }

    override fun getItem(i: Int): Any {
        return listUser[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return listUser.size
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val imgAvatar: CircleImageView = view.findViewById(R.id.img_avatar)
        private val txtName: TextView = view.findViewById(R.id.txt_name)
        private val txtLocation: TextView = view.findViewById(R.id.txt_location)
        private val txtCompany: TextView = view.findViewById(R.id.txt_company)

        internal fun bind(user: UserData) {
            user.avatar?.let { imgAvatar.setImageResource(it) }
            txtName.text = user.name
            txtLocation.text = user.location
            txtCompany.text = user.company
        }
    }

}