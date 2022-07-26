package com.example.parcocoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.parcocoon.databinding.ActivityForgotPassBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPass : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPassBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()
        binding.btnLgnFPass.setOnClickListener {
            val email=binding.etEmailFPass.text.toString()

            if(email.isNotEmpty()){
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent =Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Silahkan cek email dan ubah password anda" , Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Isi Alamat Email", Toast.LENGTH_SHORT).show()
            }

        }
    }




}