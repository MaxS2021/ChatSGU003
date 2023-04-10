package com.smastcomm.chatsgu003

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.smastcomm.chatsgu003.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        binding.btnSend.setOnClickListener {
            myRef.setValue(binding.edMessage.text.toString())
            binding.edMessage.setText("")
        }

        onChangeListener(myRef)

    }

    private fun onChangeListener(myRef: DatabaseReference) {
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.rcView.append("\n")
                binding.rcView.append("Max: ${snapshot.value.toString()}")
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}