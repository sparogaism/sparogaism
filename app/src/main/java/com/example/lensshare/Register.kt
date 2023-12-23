package com.example.lensshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_register.btnsignin
import kotlinx.android.synthetic.main.activity_register.signup

class Register : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var emaill: EditText
    private lateinit var namee: EditText
    private lateinit var sandi: EditText
    private lateinit var no_hp: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById(R.id.username)
        emaill = findViewById(R.id.emailll)
        namee = findViewById(R.id.namee)
        sandi = findViewById(R.id.sandi)
        no_hp = findViewById(R.id.no_hp)


        btnSignUpListener()
        btnLoginListener()
    }

    private fun btnSignUpListener(){
        btnsignin.setOnClickListener(){
            //startActivity(Intent(this, Home::class.java))
            val usr = username.text.toString()
            val eml = emaill.text.toString()
            val nama = namee.text.toString()
            val pass = sandi.text.toString()
            val hp = no_hp.text.toString()
            val url = "https://priogadesp4r002.000webhostapp.com/register.php"

            if (usr.isNotEmpty() && eml.isNotEmpty() && nama.isNotEmpty() && pass.isNotEmpty() && hp.isNotEmpty()){
                val stringRequest = object : StringRequest(
                    Request.Method.POST,
                    url,
                    Response.Listener<String> { response ->
                        // Menggunakan respons di sini
                        Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Login::class.java))
                    },
                    Response.ErrorListener { error ->
                        // Menangani kesalahan di sini
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    }
                ) {
                    override fun getParams(): HashMap<String, String> {
                        val params = HashMap<String, String>()
                        params["username"] = usr
                        params["email"] = eml
                        params["nama"] = nama
                        params["password"] = pass
                        params["nohp"] = hp
                        return params
                    }
                }
                val socketTimeout = 30000 // 30 detik
                val policy: RetryPolicy = DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
                stringRequest.retryPolicy = policy
                val requestQueue = Volley.newRequestQueue(this)
                requestQueue.add(stringRequest)
            }else{
                Toast.makeText(applicationContext, "Password Tidak Sama!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun btnLoginListener(){
        signup.setOnClickListener(){
            startActivity(Intent(this, Login::class.java))
        }
    }

}