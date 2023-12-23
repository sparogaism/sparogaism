package com.example.lensshare

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class listcameraadapter (var CameraList: ArrayList<modellistcamera>) : RecyclerView.Adapter<listcameraadapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val judul: TextView = itemView.findViewById(R.id.ListJudulCameraTextView)
        val btnrend: LinearLayout = itemView.findViewById(R.id.btnlistcameralinearlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_camera, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return CameraList.size
    }

    override fun onBindViewHolder(holder: listcameraadapter.ViewHolder, position: Int) {
        val infocamera = CameraList[position]

        holder.judul.text = infocamera.namacamera
        holder.btnrend.setOnClickListener {
            showConfirmationDialog(holder.itemView.context)
        }
    }

    private fun showConfirmationDialog(context: Context) {
        AlertDialog.Builder(context)
            .setMessage("Apakah Anda yakin ingin melanjutkan?")
            .setPositiveButton("Ya") { _, _ ->
                val intent = Intent(context, Pending::class.java)
                context.startActivity(intent)
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                // Tindakan yang akan diambil jika user menekan tombol "Tidak"
                dialog.dismiss() // Menutup dialog
            }
            .show()
    }

}