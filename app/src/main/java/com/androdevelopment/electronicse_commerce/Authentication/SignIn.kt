package com.androdevelopment.electronicse_commerce.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.androdevelopment.electronicse_commerce.Activities.MainActivity
import com.androdevelopment.electronicse_commerce.databinding.ActivitySignInBinding

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        startActivity(Intent(this, MainActivity::class.java))
        finish()

        setUpSingIn()
        setUpSignUp()
        setForgotPassword()
    }

    private fun setForgotPassword() {
        binding.textViewSignInForgotPassword.setOnClickListener {
            Toast.makeText(this@SignIn,"...",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpSignUp() {
        binding.textViewSignUp.setOnClickListener {
            startActivity(Intent(this@SignIn,SignUp::class.java))
        }
    }

    private fun setUpSingIn() {
        binding.buttonSignIn.setOnClickListener {

            if (binding.editTextTextSignInEmail.text.isEmpty()) {
                binding.editTextTextSignInEmail.error = "Invalid Email"
                return@setOnClickListener
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextTextSignInEmail.text)
                    .matches()
            ) {
                binding.editTextTextSignInEmail.error = "Invalid Email"
                return@setOnClickListener
            } else if (binding.editTextTextSignInPassword.text.isEmpty()) {
                binding.editTextTextSignInPassword.error = "Invalid Password"
                return@setOnClickListener
            }
            startActivity(Intent(this@SignIn, MainActivity::class.java))
        }
    }
}