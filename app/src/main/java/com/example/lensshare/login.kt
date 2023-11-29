package com.example.lensshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLoginListener()
    }

    private fun btnLoginListener(){
        btn.setOnClickListener {
            startActivity(this, )
        }
    }
}