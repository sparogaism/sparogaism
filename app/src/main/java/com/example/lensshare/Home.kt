package com.example.lensshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.signupnow


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnUserListener()
        btnListListener()
        btnProsesListener()
        }

    private fun btnUserListener(){
        shapeProfile.setOnClickListener {
            startActivity(Intent(this, User::class.java))
        }

        }

    private fun btnListListener(){
        shapeList.setOnClickListener {
            startActivity(Intent(this, List::class.java))
        }

        }

    private fun btnProsesListener(){
        shapeProses.setOnClickListener {
            startActivity(Intent(this, Process::class.java))
        }

    }

}


