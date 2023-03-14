package com.example.mynoteapps.util

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapps.data.NoteRepository
import com.example.mynoteapps.di.Injection
import com.example.mynoteapps.viewmodel.NoteViewModel

class NoteFactory(private val repository: NoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

    companion object {
        @Volatile
        private var instance: NoteFactory? = null
        fun getInstance(context: Context): NoteFactory =
            instance ?: synchronized(this) {
                instance ?: NoteFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}