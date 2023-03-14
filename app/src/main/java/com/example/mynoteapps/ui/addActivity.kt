package com.example.mynoteapps.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mynoteapps.R
import com.example.mynoteapps.data.local.entity.NoteEntity
import com.example.mynoteapps.databinding.ActivityAddBinding
import com.example.mynoteapps.util.NoteFactory
import com.example.mynoteapps.viewmodel.NoteViewModel

class addActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: NoteFactory = NoteFactory.getInstance(this)
        val viewModel =  ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        binding.addbutton.setOnClickListener {
            val title = binding.titleText.text
            val desc = binding.descText.text
            val note = NoteEntity(0,title.toString(), desc.toString())
            viewModel.insertNote(note)

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}