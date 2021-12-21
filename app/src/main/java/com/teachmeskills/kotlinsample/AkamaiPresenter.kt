package com.teachmeskills.kotlinsample

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.*
import java.net.URL

interface AkamaiView {
    fun setTime(time: String)
}

class AkamaiPresenter : LifecycleEventObserver {

    var view: AkamaiView? = null

    private var time: String = ""
        set(value) {
            field = value
            view?.setTime(time)
        }
    private val scope = CoroutineScope(Dispatchers.Main)

    fun syncTime() {
        scope.launch {
            time = "loading..."
            time = withContext(Dispatchers.IO) {
                //активити пересоздалась
                delay(3000)

                try {
                    URL("https://time.akamai.com/").readText()
                } catch (e: Exception) {
                    "Error"
                }
            }
        }
    }

    fun onDestroy() {
        scope.cancel()
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            view?.setTime(time)
        }
    }
}