package com.teachmeskills.kotlinsample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AkamaiPresenterActivity : AppCompatActivity(), AkamaiView {
    private lateinit var presenter: AkamaiPresenter
    private lateinit var timeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akamai)

        presenter = if (savedInstanceState == null) {
            AkamaiPresenter()
        } else {
            lastCustomNonConfigurationInstance as AkamaiPresenter
        }

        timeTextView = findViewById(R.id.time)
        presenter.view = this
        findViewById<Button>(R.id.syncTime).setOnClickListener {
            presenter.syncTime()
        }
        lifecycle.addObserver(presenter)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            presenter.onDestroy()
        }
        presenter.view = null
    }

    override fun setTime(time: String) {
        timeTextView.text = time
    }
}