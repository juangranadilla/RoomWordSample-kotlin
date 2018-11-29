package com.juangm.roomwordsample.data.data.local

import android.os.AsyncTask
import com.juangm.roomwordsample.data.data.local.WordDao
import com.juangm.roomwordsample.data.data.local.WordRoomDatabase
import com.juangm.roomwordsample.data.ui.models.Word

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