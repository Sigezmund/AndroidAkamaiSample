package com.teachmeskills.kotlinsample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.teachmeskills.kotlinsample.customlivedata.CustomLiveDataActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.openViewModel).setOnClickListener {
            startActivity(Intent(this, AkamaiViewModelActivity::class.java))
        }

        findViewById<Button>(R.id.openPresenter).setOnClickListener {
            startActivity(Intent(this, AkamaiPresenterActivity::class.java))
        }

        findViewById<Button>(R.id.openCustomLiveDataViewModel).setOnClickListener {
            startActivity(Intent(this, CustomLiveDataActivity::class.java))
        }
    }


}