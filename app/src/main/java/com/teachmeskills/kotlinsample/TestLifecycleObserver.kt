package com.teachmeskills.kotlinsample

import androidx.lifecycle.*
import com.teachmeskills.kotlinsample.extensions.log

class TestLifecycleObserver : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        log(this, event.toString())
    }
}

class DeprecatedLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {

    }
}