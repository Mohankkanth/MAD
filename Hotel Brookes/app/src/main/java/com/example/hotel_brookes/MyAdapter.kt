package com.example.hotel_brookes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val requestList: ArrayList<UserValues>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

//    var single_item_position = -1

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvRoom: TextView = itemView.findViewById(R.id.tv_room)
        val tvPhone: TextView = itemView.findViewById(R.id.tv_phone)
        val tvEmail: TextView = itemView.findViewById(R.id.tv_email)
        val tvchin: TextView = itemView.findViewById(R.id.tv_Checkin)
        val tvchout: TextView = itemView.findViewById(R.id.tv_checkout)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView  = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return  MyViewHolder(itemView)
    }

    /*override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val request = requestList[position]
        holder.tvName.text = request.name

        holder.itemView.setOnClickListener {
            onItemClick(request.name)
        }
    }*/

    override fun getItemCount(): Int {
        return requestList.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val rooms = requestList[position]

        holder.tvName.text = "Name : ${rooms.name}"
        holder.tvRoom.text = "RoomNo : ${rooms.roomno}"
        holder.tvPhone.text = "Mobile : ${rooms.mobile}"
        holder.tvEmail.text = "Email : ${rooms.email}"
        holder.tvchin.text = "CheckIn : ${rooms.checkin}"
        holder.tvchout.text = "CheckOut : ${rooms.checkout}"

    }
}