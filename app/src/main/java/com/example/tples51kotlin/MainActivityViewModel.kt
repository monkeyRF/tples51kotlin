package com.example.tples51kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    //
    private val textMutableLiveData = MutableLiveData<String>()

    val textMessage: LiveData<String>
        get() = textMutableLiveData

    fun setTextMessage(text: String) {
        textMutableLiveData.value = text
    }
}