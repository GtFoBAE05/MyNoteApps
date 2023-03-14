package com.example.mynoteapps.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynoteapps.R
import com.example.mynoteapps.adapter.NoteAdapter
import com.example.mynoteapps.data.local.entity.NoteEntity
import com.example.mynoteapps.databinding.ActivityMainBinding
import com.example.mynoteapps.util.NoteFactory
import com.example.mynoteapps.util.Resource
import com.example.mynoteapps.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: NoteFactory = NoteFactory.getInstance(this)
        val viewModel =  ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        val rv = binding.noteRv
        rv.layoutManager = LinearLayoutManager(this)

        binding.addButton.setOnClickListener {
            val intent = Intent(this,addActivity::class.java)
            startActivity(intent)
        }



        viewModel.getAllNotes().observe(this,{
            if(it!=null){
                when(it){
                    is Resource.Loading ->{
                        Toast.makeText(this,"Loading", Toast.LENGTH_SHORT).show()
                        binding.progressBar2.visibility = View.VISIBLE
                    }

                    is Resource.Error ->{
                        Toast.makeText(this,"ERROR", Toast.LENGTH_SHORT).show()
                        binding.progressBar2.visibility = View.INVISIBLE
                    }

                    is Resource.Success<*> ->{
                        if(it.data!=null){
                            val data = it.data
                            val adapter = NoteAdapter(data)
                            rv.adapter = adapter
                            Toast.makeText(this,"SUCCES", Toast.LENGTH_SHORT).show()
                            binding.progressBar2.visibility = View.INVISIBLE
                        }else{
                            Toast.makeText(this,"DATA KOSONG", Toast.LENGTH_SHORT).show()
                            binding.progressBar2.visibility = View.INVISIBLE
                        }

                    }
                }
            }


        })


//        viewModel.getAllNotes().observe(this,{
//            binding.progressBar2.visibility = View.INVISIBLE
//            val adapter = NoteAdapter(it)
//            rv.adapter = adapter
//        })


    }
}