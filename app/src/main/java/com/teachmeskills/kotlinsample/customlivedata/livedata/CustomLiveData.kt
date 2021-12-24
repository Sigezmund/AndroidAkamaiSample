package com.teachmeskills.kotlinsample.customlivedata.livedata

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class CustomLiveData {

    private val observersSet = mutableSetOf<ObserverWrapper>()

    var value: String? = null
        set(value) {
            field = value
            // Когда значение поменялось, оповещаем всех слушателей об этом
            observersSet.forEach {
                it.onValueChanged(field)
            }
        }

    fun observer(lifecycleOwner: LifecycleOwner, observer: Observer) {

        if (lifecycleOwner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
            return
        }
        val wrapper = ObserverWrapper(lifecycleOwner, observer)
        observersSet.add(wrapper)
        lifecycleOwner.lifecycle.addObserver(wrapper)
    }

    fun removeObserver(observer: ObserverWrapper) {
        observersSet.remove(observer)
    }

    // Wrapper над нашим слушателем. Содержит в себе слушателя и lifecycleOwner
    inner class ObserverWrapper(
        private val lifecycleOwner: LifecycleOwner,
        private val observer: Observer
    ) : LifecycleEventObserver {
        private var isObserverActive = false

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_DESTROY -> {
                    // Удаляем слушателя, когда activity уничтожилась
                    removeObserver(this)
                }
                else -> {

                    val isLifecycleActive = isLifecycleActive()
                    if (isLifecycleActive != isObserverActive) {
                        // Оповещаем случателя, если наше состояние поменялось и
                        // оно отлиается от текущего состояния
                        isObserverActive = isLifecycleActive
                        onValueChanged(value)
                    }
                }
            }
        }

        fun onValueChanged(newValue: String?) {
            // Оповещаем только тогда, когда activity находится в состоянии STARTED
            if (isObserverActive) {
                observer.onChanged(newValue)
            }
        }

        fun isLifecycleActive(): Boolean {

            // Проверяем текущее состояние activity. isAtLeast вернут truе, если наше текущее состояние
            // будет STARTED или RESUMED и false, если CREATED или DESTROYED
            return lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
        }

    }
}


interface Observer {
    fun onChanged(value: String?)
}