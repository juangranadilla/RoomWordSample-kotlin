package com.juangm.roomwordsample.data.local

import android.os.AsyncTask
import com.juangm.roomwordsample.ui.models.Word

class PopulateDbAsync internal constructor(db: WordRoomDatabase?) : AsyncTask<Void, Void, Void>() {

    private val mDao = db?.wordDao()

    override fun doInBackground(vararg params: Void): Void? {
        mDao?.deleteAll()
        var word = Word("Hello")
        mDao?.insert(word)
        word = Word("World")
        mDao?.insert(word)
        return null
    }
}