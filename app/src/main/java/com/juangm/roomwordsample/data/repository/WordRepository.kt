package com.juangm.roomwordsample.data.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.juangm.roomwordsample.ui.models.Word
import com.juangm.roomwordsample.data.local.WordDao
import com.juangm.roomwordsample.data.local.WordRoomDatabase

class WordRepository internal constructor(application: Application) {

    private val mWordDao: WordDao?
    private val mAllWords: LiveData<List<Word>>?

    init {
        val db = WordRoomDatabase.getDatabase(application)
        mWordDao = db?.wordDao()
        mAllWords = mWordDao?.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>>? {
        return mAllWords
    }

    fun insert(word: Word) {
        insertAsyncTask(mWordDao).execute(word)
    }

    class insertAsyncTask(private val mAsyncTaskDao: WordDao?) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao?.insert(params[0])
            return null
        }
    }
}