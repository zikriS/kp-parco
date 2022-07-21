package com.example.parcocoon

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import com.example.parcocoon.databinding.ActivityRegisterPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterPage : AppCompatActivity() {
    // pada car binding activity yang diambil ialah binding
    private lateinit var binding: ActivityRegisterPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvOnClickLogin.setOnClickListener {
            //pada binding setonclick pakai activity dari class
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.SignUpbtn.setOnClickListener {
            val user = binding.editTextTextPersonName.text.toString()
            val email = binding.editTextInputEmail.text.toString()
            val pass = binding.editTextInputPass.text.toString()
            val confirmpass = binding.editTextInputRetypePass.text.toString()



            if (email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty()) {
                if (pass == confirmpass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, LoginActivity::class.java)
                            uploadData(user,email)
                            Toast.makeText(this, "Daftar berhasil, Silahkan login menggunakan akun anda", Toast.LENGTH_SHORT).show()

                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }

                } else {
                    Toast.makeText(this, "Password harus sama", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Area tidak boleh kosong !!", Toast.LENGTH_SHORT).show()

            }

        }

    }

    private fun uploadData(user: String, email: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        val User =  mapOf<String,String>(
            "User" to user,
            "Email" to email

        )
        val a = "https://kp-parcocoon-default-rtdb.asia-southeast1.firebasedatabase.app"
        Firebase.database.getReference("user").child(firebaseAuth.currentUser!!.uid).setValue(user).addOnSuccessListener {
            Log.d(TAG, "uploadData: regis sukses")
            startActivity(intent)
        }.addOnFailureListener{
            Log.w(TAG, "uploadData: ${it.message}" )
        }

    }
}