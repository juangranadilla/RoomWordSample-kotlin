package com.juangm.roomwordsample.data.local

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.content.Context
import android.arch.persistence.db.SupportSQLiteDatabase
import com.juangm.roomwordsample.ui.models.Word

@Database(entities = arrayOf(Word::class), version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        private var INSTANCE: WordRoomDatabase? = null

        val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE)
                    .execute()
            }
        }

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase::class.java, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE
        }
    }
}