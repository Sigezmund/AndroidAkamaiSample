package com.teachmeskills.kotlinsample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.teachmeskills.kotlinsample.extensions.testFun

class AkamaiViewModelActivity : AppCompatActivity() {

    private lateinit var timeTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akamai)
        timeTextView = findViewById(R.id.time)
        val viewModel = ViewModelProvider(this).get(AkamaiViewModel::class.java)
        findViewById<Button>(R.id.syncTime).setOnClickListener {
            viewModel.syncTime()
        }
        viewModel.time.observe(this) {
            timeTextView.text = it
        }

        viewModel.testFun()
    }


//    private lateinit var presenter: AkamaiPresenter
//    private lateinit var timeTextView: TextView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_akamai)
//
//        presenter = if (savedInstanceState == null) {
//            AkamaiPresenter()
//        } else {
//            lastCustomNonConfigurationInstance as AkamaiPresenter
//        }
//
//        timeTextView = findViewById(R.id.time)
//        presenter.view = this
//        findViewById<Button>(R.id.syncTime).setOnClickListener {
//            presenter.syncTime()
//        }
//        lifecycle.addObserver(presenter)
//    }
//
//    override fun onRetainCustomNonConfigurationInstance(): Any? {
//        return presenter
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (isFinishing) {
//            presenter.onDestroy()
//        }
//        presenter.view = null
//    }
//
//    override fun setTime(time: String) {
//        timeTextView.text = time
//    }

}