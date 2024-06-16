package com.bangkit.application.view.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bangkit.application.R
import com.bangkit.application.data.pref.UserModel
import com.bangkit.application.data.remote.response.LoginResponse
import com.bangkit.application.databinding.ActivityLoginBinding
import com.bangkit.application.databinding.ActivitySignupBinding
import com.bangkit.application.view.ViewModelFactory
import com.bangkit.application.view.main.MainActivity
import com.bangkit.application.view.signup.SignupActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupAction(){
        binding.button.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val valid = validate(username, password)

            if (valid) {
                val factory: ViewModelFactory = ViewModelFactory.getInstance(this)

                val viewModel: LoginViewModel by viewModels {
                    factory
                }

                lifecycleScope.launch {
                    try {
                        //get success message
                        val response = viewModel.login(username, password)
                        val message = response.message
                        message?.let { showSuccessDialog() }
                        response.data?.accessToken?.let { viewModel.saveSession(UserModel(username, response.data.accessToken)) }
                    } catch (e: HttpException) {
                        //get error message
                        val jsonInString = e.response()?.errorBody()?.string()
                        val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
                        val errorMessage = errorBody.message
                        errorMessage?.let {showErrorDialog(it)}
                    }
                }
            }
        }

        binding.textViewRegister.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
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

    private fun showSuccessDialog(){
        AlertDialog.Builder(this).apply {
            setTitle("Yeah!")
            setMessage("Anda berhasil login. Yuk kelola uangmu dengan mudah!")
            setPositiveButton("Lanjut") { _, _ ->
                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            create()
            show()
        }

    }

    private fun showErrorDialog(errorMessage: String) {
        // Using 'this@MainActivity' to get the Context within an Activity
        AlertDialog.Builder(this).apply {
            setTitle("Error")
            setMessage(errorMessage)
            setPositiveButton("OK", null)
            create()
            show()
        }
    }

    private fun validate(username: String, password: String): Boolean {
        var valid = true;

        if (username == ""){
            binding.username.error = "Username harus diisi"
            valid = false
        } else {
            binding.username.error = null
        }

        if (password == ""){
            binding.password.error = "Password harus diisi"
            valid = false
        } else {
            binding.password.error = null
        }

        return valid
    }

}