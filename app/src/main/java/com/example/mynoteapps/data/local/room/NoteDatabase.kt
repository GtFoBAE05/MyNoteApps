package com.example.mynoteapps.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynoteapps.data.local.entity.NoteEntity


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false )
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao():NoteDao

    companion object{
        @Volatile
        private var instance: NoteDatabase? = null
        fun getInstance(context: Context):NoteDatabase =
            instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java, "Notes.db"
                ).build()
            }
    }
}