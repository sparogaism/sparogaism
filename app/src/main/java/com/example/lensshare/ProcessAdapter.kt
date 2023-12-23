package com.example.lensshare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProcessAdapter (var CameraList: ArrayList<ModelProcess>) : RecyclerView.Adapter<ProcessAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val judul: TextView = itemView.findViewById(R.id.ListJudulCameraprocessTextView)
        val statusc: TextView = itemView.findViewById(R.id.ListStatusCameraprocessTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_process, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return CameraList.size
    }

    override fun onBindViewHolder(holder: ProcessAdapter.ViewHolder, position: Int) {
        val infocamera = CameraList[position]

        holder.judul.text = infocamera.namacamera
        holder.statusc.text = infocamera.statuscamera
    }

}