package com.juangm.roomwordsample.ui.wordlist

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.AndroidViewModel
import com.juangm.roomwordsample.data.repository.WordRepository
import com.juangm.roomwordsample.ui.models.Word

class WordListViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = WordRepository(application)
    private val allWords = mRepository.getAllWords()

    fun insert(word: Word) {
        mRepository.insert(word)
    }

    fun getAllWords(): LiveData<List<Word>>? {
        return allWords
    }
}