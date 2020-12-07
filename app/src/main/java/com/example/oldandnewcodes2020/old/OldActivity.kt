package com.example.oldandnewcodes2020.old

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oldandnewcodes2020.R

class OldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.old_main_container, OldMainFragment())
        transaction.commit()
    }
}