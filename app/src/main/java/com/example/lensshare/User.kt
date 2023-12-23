package com.example.lensshare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.signupnow
import kotlinx.android.synthetic.main.activity_main.btn_1
import kotlinx.android.synthetic.main.activity_user.backHome
import kotlinx.android.synthetic.main.activity_user.buttonLog

class User : AppCompatActivity() {

    private lateinit var namauser: TextView
    private lateinit var emaiuser: TextView
    private lateinit var nohp: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        namauser = findViewById(R.id.nameUser)
        emaiuser = findViewById(R.id.emailUser)
        nohp = findViewById(R.id.noHp)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val nama = sharedPreferences.getString("nama", "")
        val email = sharedPreferences.getString("email", "")
        val hp = sharedPreferences.getString("nohp", "")

        namauser.setText("$nama")
        emaiuser.setText("$email")
        nohp.setText("$hp")

        btnHomeListener()
        btnLogoutListener()
    }

    private fun btnHomeListener() {
        backHome.setOnClickListener() {
            startActivity(Intent(this, Home::class.java))

        }

    }

    private fun btnLogoutListener(){
        buttonLog.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    startActivity(Intent(this, MainActivity::class.java))
                    finish() // Menutup aktivitas
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    // Tindakan yang akan diambil jika user menekan tombol "Tidak"
                    dialog.dismiss() // Menutup dialog
                }
                .show()
        }
    }
}