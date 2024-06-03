package com.bangkit.application.view.signup

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bangkit.application.R
import com.bangkit.application.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction(){
        binding.button.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val telp = binding.telpInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val confirm = binding.confirmInput.text.toString()

            if (password != confirm){
                binding.confirm.error = "Password tidak sesuai"
            } else {
                binding.confirm.error = null
            }

        }

        binding.textViewLogin.setOnClickListener {
            finish()
        }
    }
}