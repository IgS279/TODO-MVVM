package com.igld279.todo_mvvm.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "https://github.com/IgS279"
    }
    val text: LiveData<String> = _text
}