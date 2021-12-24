package com.teachmeskills.kotlinsample.customlivedata

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.teachmeskills.kotlinsample.R
import com.teachmeskills.kotlinsample.customlivedata.livedata.Observer

class CustomLiveDataActivity : AppCompatActivity() {

    private lateinit var data: TextView
    private val customLiveDataViewModel by lazy {
        ViewModelProvider(this)
            .get(CustomLiveDataViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_live_data)

        data = findViewById(R.id.data)

        customLiveDataViewModel.someData.observer(this, object : Observer {
            override fun onChanged(value: String?) {
                data.text = value
            }
        })
    }

}