package jp.takakic.easytraining.ui.eating

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class EatingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is eating Fragment"
    }
    val text: LiveData<String> = _text
}