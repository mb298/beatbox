package com.bignerdranch.android.beatbox

import android.content.res.AssetManager
import android.media.SoundPool
import java.lang.Exception

private const val SOUNDS_FOLDER = "sample_sounds"
private const val MAX_SOUNDS = 5

class BeatBox(private val assets: AssetManager) {



    val sounds : List<Sound>

    private val soundPool: SoundPool = SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build()

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {
        return try {
            val soundNames = assets.list(SOUNDS_FOLDER)!!
            soundNames.asList().map { fileName ->
                Sound("$SOUNDS_FOLDER/$fileName").apply { load(this) }
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    private fun load(sound: Sound) {
        sound.soundId = soundPool.load(assets.openFd(sound.assetPath), 1)
    }

    fun play(sound:Sound) {
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1,0,1.0f)
        }
    }

    fun release() {
        soundPool.release()
    }
}
