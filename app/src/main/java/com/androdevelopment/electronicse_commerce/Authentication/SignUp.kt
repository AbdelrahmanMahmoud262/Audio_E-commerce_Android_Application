package com.androdevelopment.electronicse_commerce.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.androdevelopment.electronicse_commerce.Activities.MainActivity
import com.androdevelopment.electronicse_commerce.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setUpSignIn()
        setUpSingUp()
    }

    private fun setUpSignIn() {
        binding.textViewSignIn.setOnClickListener {
            startActivity(Intent(this@SignUp, SignIn::class.java))
        }
    }

    private fun setUpSingUp() {
        binding.buttonSignUp.setOnClickListener {

            if (binding.editTextTextSignUpEmail.text.isEmpty()) {
                binding.editTextTextSignUpEmail.error = "Invalid Email"
                return@setOnClickListener
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextTextSignUpEmail.text)
                    .matches()
            ) {
                binding.editTextTextSignUpEmail.error = "Invalid Email"
                return@setOnClickListener
            } else if (binding.editTextTextSignUpPassword.text.isEmpty()) {
                binding.editTextTextSignUpPassword.error = "Invalid Password"
                return@setOnClickListener
            }
        startActivity(Intent(this@SignUp, MainActivity::class.java))
    }
}
}