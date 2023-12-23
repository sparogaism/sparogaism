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

class Process : AppCompatActivity() {

    private lateinit var recyclerViewCamera: RecyclerView
    private lateinit var adapter: ProcessAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_process)

        btnBackListener()

        recyclerViewCamera = findViewById(R.id.recycleviewlistprocess)
        recyclerViewCamera.layoutManager = LinearLayoutManager(this)
        adapter = ProcessAdapter(ArrayList())
        recyclerViewCamera.adapter = adapter

        val url = "https://priogadesp4r002.000webhostapp.com/camera.php"
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonObject = JSONObject(response)
                if (jsonObject.has("data")) {
                    val jsonArray = jsonObject.getJSONArray("data")
                    val CameraList = ArrayList<ModelProcess>()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val idcamera = jsonObject.getInt("id_camera")
                        val namacamera = jsonObject.getString("nama_camera")
                        val statuscamera = jsonObject.getString("status")
                        CameraList.add(ModelProcess(idcamera, namacamera, statuscamera))
                    }
                    adapter.CameraList = CameraList
                    adapter.notifyDataSetChanged()
                } else {
                    val CameraList = ArrayList<ModelProcess>()
                    CameraList.add(ModelProcess(0, "No Care available", "No Care available"))
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
}