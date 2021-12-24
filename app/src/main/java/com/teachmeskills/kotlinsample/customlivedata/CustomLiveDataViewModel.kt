package com.teachmeskills.kotlinsample.customlivedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teachmeskills.kotlinsample.customlivedata.livedata.CustomLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CustomLiveDataViewModel : ViewModel() {

    val someData = CustomLiveData()

    init {
        viewModelScope.launch {
            var index = 0
            //Каждые 3 секунды добавляем в someData новое значение.
            while (true) {
                delay(3000)
                someData.value = "Stas$index"
                index++
            }
        }
    }

}