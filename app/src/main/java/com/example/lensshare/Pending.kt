package com.example.lensshare

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list.backButton
import kotlinx.android.synthetic.main.activity_pending.homeWa
import kotlinx.android.synthetic.main.activity_pending.whatsappButton
import java.net.URLEncoder

class Pending : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending)

        btnHomeListener()
        btnWhatsappListener(this)
    }



    private fun btnHomeListener(){
        homeWa.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }

    }

    private fun btnWhatsappListener(context: Context) {
        whatsappButton.setOnClickListener {
            val phoneNumber = "62895618996649" // Ganti dengan nomor telepon tujuan
            val message = "Halo, saya ingin merental camera!" // Ganti dengan pesan template Anda

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://wa.me/$phoneNumber/?text=${URLEncoder.encode(message, "UTF-8")}")

            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            } else {
                // Aplikasi WhatsApp tidak terinstal
                Toast.makeText(context, "Aplikasi WhatsApp tidak terinstal.", Toast.LENGTH_SHORT).show()
            }
        }
    }



}