package com.example.mynoteapps.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.mynoteapps.data.NoteRepository
import com.example.mynoteapps.data.local.entity.NoteEntity
import com.example.mynoteapps.util.Resource
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository):ViewModel() {


//    private var _userList = MutableLiveData<Resource<List<NoteEntity>>>()
//    val userList : LiveData<Resource<List<NoteEntity>>> = _userList

    init {
        Log.e(TAG, "noteviewmodel: "+ noteRepository.getAllNotes(), )
        getAllNotes()
    }

    fun getAllNotes() = noteRepository.getAllNotes()


//    fun getAllNotes(){
//        _userList.value = Resource.Loading
//        try {
//            _userList.value = noteRepository.getAllNotes()
//        }
//    }


//    fun getAllNotes() {
//        viewModelScope.launch{
//            _userList.postValue(noteRepository.getAllNotes())
//        }
//
//    }


    fun insertNote(noteEntity: NoteEntity){
        viewModelScope.launch {
            noteRepository.addNote(noteEntity)
        }
    }

}