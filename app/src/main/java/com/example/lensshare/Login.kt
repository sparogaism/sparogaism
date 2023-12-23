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
import kotlinx.android.synthetic.main.activity_login.btnsign
import kotlinx.android.synthetic.main.activity_login.signupnow
import org.json.JSONObject

class Login : AppCompatActivity() {

    private lateinit var emaill: EditText
    private lateinit var pw: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emaill = findViewById(R.id.emaill)
        pw = findViewById(R.id.pw)

        btnLoginListener()
        btnSignInListener()
    }

    private fun btnLoginListener(){
        btnsign.setOnClickListener {
            //startActivity(Intent(this, Home::class.java))
            val email = emaill.text.toString()
            val pass = pw.text.toString()
            val url = "https://priogadesp4r002.000webhostapp.com/login.php"

            if (email.isNotEmpty() && pass.isNotEmpty()){
                val stringRequest = object : StringRequest(
                    Request.Method.POST,
                    url, Response.Listener<String> { response ->
                        // Menggunakan respons di sini
                        val jsonResponse = JSONObject(response)
                        val status = jsonResponse.getString("status")

                        if (status == "success") {
                            val idAkun = jsonResponse.getString("id_akun")
                            val usr = jsonResponse.getString("username")
                            val eml = jsonResponse.getString("email")
                            val nama = jsonResponse.getString("nama")
                            val nohp = jsonResponse.getString("nohp")

                            // Simpan id_akun dan nama_akun di sini, misalnya menggunakan SharedPreferences
                            val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("id_akun", idAkun)
                            editor.putString("username", usr)
                            editor.putString("email", eml)
                            editor.putString("nama", nama)
                            editor.putString("nohp", nohp)
                            editor.apply()

                            Toast.makeText(applicationContext, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, Home::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show()
                        }
                    },
                    Response.ErrorListener { error ->
                        // Menangani kesalahan di sini
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    }
                ) {
                    override fun getParams(): HashMap<String, String> {
                        val params = HashMap<String, String>()
                        params["email"] = email
                        params["password"] = pass
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
                Toast.makeText(applicationContext, "Data Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun btnSignInListener(){
        signupnow.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

}