package com.teachmeskills.kotlinsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.net.URL



class AkamaiViewModel : ViewModel() {

    val time = MutableLiveData<String>()

    private val scope = CoroutineScope(Dispatchers.Main)
    fun syncTime() {
        scope.launch {
            time.value = "loading..."
            time.value = withContext(Dispatchers.IO) {
                //активити пересоздалась
                delay(3000)

                try {
                    URL("https://time.akamai.com/").readText()
                } catch (e: Exception) {
                    "Error"
                }
            }.orEmpty()
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}