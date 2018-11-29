package com.juangm.roomwordsample.ui.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "word_table")
class Word(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    val word: String
)