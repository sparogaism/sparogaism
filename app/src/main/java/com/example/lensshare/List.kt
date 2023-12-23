package com.example.lensshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_list.backButton
import kotlinx.android.synthetic.main.activity_list.recycleviewlist
import org.json.JSONObject

class List : AppCompatActivity() {

    private lateinit var recyclerViewCamera: RecyclerView
    private lateinit var adapter: listcameraadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        btnBackListener()
//        btnRent1Listener()
//        btnRent2Listener()
//        btnRent3Listener()

        recyclerViewCamera = findViewById(R.id.recycleviewlist)
        recyclerViewCamera.layoutManager = LinearLayoutManager(this@List)
        adapter = listcameraadapter(ArrayList())
        recycleviewlist.adapter = adapter

        val url = "https://priogadesp4r002.000webhostapp.com/camera.php"
        val requestQueue = Volley.newRequestQueue(this@List)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonObject = JSONObject(response)
                if (jsonObject.has("data")) {
                    val jsonArray = jsonObject.getJSONArray("data")
                    val CameraList = ArrayList<modellistcamera>()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val idcamera = jsonObject.getInt("id_camera")
                        val namacamera = jsonObject.getString("nama_camera")
                        val statuscamera = jsonObject.getString("status")
                        CameraList.add(modellistcamera(idcamera, namacamera, statuscamera))
                    }
                    adapter.CameraList = CameraList
                    adapter.notifyDataSetChanged()
                } else {
                    val CameraList = ArrayList<modellistcamera>()
                    CameraList.add(modellistcamera(0, "No Care available", "No Care available"))
                }
            },
            { error ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            })
        requestQueue.add(stringRequest)
    }

    private fun btnBackListener(){
        backButton.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

    }

//    private fun btnRent1Listener(){
//        rentButton.setOnClickListener {
//            startActivity(Intent(this, Pending::class.java))
//        }
//
//    }
//
//    private fun btnRent2Listener(){
//        rentButton2.setOnClickListener {
//            startActivity(Intent(this, Pending::class.java))
//        }
//
//    }
//
//    private fun btnRent3Listener(){
//        rentButton3.setOnClickListener {
//            startActivity(Intent(this, Pending::class.java))
//        }
//
//    }

}