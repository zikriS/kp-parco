package com.example.parcocoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.parcocoon.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth.currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

        //gotoregister text view
        binding.gotoRegister.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }
        //gotoforgotpassword
        binding.tvForgotPass.setOnClickListener {
            val intent = Intent(this, ForgotPass::class.java)
            startActivity(intent)
        }
        //button untuk login
        binding.loginBtn.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val pass = binding.inputPassword.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Selamat Datang", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(
                            this,
                            "Email atau Password yang anda masukan SALAH",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }


            } else {
                Toast.makeText(this, "Area tidak boleh kosong !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
}