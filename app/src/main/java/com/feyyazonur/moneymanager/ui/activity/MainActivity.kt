package com.feyyazonur.moneymanager.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feyyazonur.moneymanager.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }

}