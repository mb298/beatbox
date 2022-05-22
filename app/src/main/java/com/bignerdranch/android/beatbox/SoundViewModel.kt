package com.bignerdranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData

class SoundViewModel(private val beatBox:BeatBox)  {

    val title:MutableLiveData<String?> = MutableLiveData()

    var sound: Sound? = null
        set(sound) {
            field = sound
            title.value = sound?.name
        }

    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }

}
