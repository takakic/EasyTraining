package jp.takakic.easytraining.ui.body

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class BodyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "身体登録画面"
    }
    val text: LiveData<String> = _text
}