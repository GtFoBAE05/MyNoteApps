package com.example.mynoteapps.di

import android.content.Context
import com.example.mynoteapps.data.NoteRepository
import com.example.mynoteapps.data.local.room.NoteDatabase

object Injection {
    fun provideRepository(context:Context):NoteRepository{
        val noteDatabase = NoteDatabase.getInstance(context)
        val noteDao = noteDatabase.noteDao()
        return NoteRepository.getInstance(noteDao)
    }
}