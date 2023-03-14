package com.example.mynoteapps.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.mynoteapps.data.local.entity.NoteEntity
import com.example.mynoteapps.data.local.room.NoteDao
import com.example.mynoteapps.util.Resource

class NoteRepository private constructor(
    private val noteDao: NoteDao
){


    //fun getAllNotes() : LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    fun getAllNotes() = liveData {
        emit(Resource.Loading)
        try {
            val data = noteDao.getAllNotes().map{Resource.Success(it)}
            emit(data.value)
            Log.e(TAG, "getAllNotesrepository success"+data, )
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
            Log.e(TAG, "getAllNotesRepositoryError: " + e.message, )
        }
    }


//    fun getAllNotes() = liveData<Resource<List<NoteEntity>>> {
//        emit(Resource.Loading)
//        try {
//            val data = noteDao.getAllNotes()
//            emit(Resource.Success(data))
//        }catch (e:Exception){
//            emit(Resource.Error(e.message.toString()))
//            Log.e(TAG, "getAllNotesRepositoryError: " + e.message, )
//        }
//    }

    suspend fun addNote(noteEntity: NoteEntity) = noteDao.addNote(noteEntity)


    companion object{
        @Volatile
        private var instance: NoteRepository? = null
        fun getInstance(
            noteDao: NoteDao
        ): NoteRepository =
            instance ?: synchronized(this){
                instance ?: NoteRepository(noteDao)
            }.also { instance = it }
    }

}