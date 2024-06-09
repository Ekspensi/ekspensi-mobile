package com.bangkit.application.view.signup

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
import com.bangkit.application.data.remote.response.RegisterResponse
import com.bangkit.application.databinding.ActivitySignupBinding
import com.bangkit.application.view.ViewModelFactory
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
            val valid = validate(username, telp, password, confirm);

            if (valid){
                val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
                val viewModel: SignupViewModel by viewModels {
                    factory
                }

                lifecycleScope.launch {
                    try {
                        val message = viewModel.register(username, telp, password)
                        message?.let { showSuccessDialog(username) }
                    } catch (e: HttpException) {
                        val jsonInString = e.response()?.errorBody()?.string()
                        val errorBody = Gson().fromJson(jsonInString, RegisterResponse::class.java)
                        val errorMessage = errorBody.message
                        errorMessage?.let {showErrorDialog(it)}
                    }
                }
            }
        }
        binding.textViewLogin.setOnClickListener {
            finish()
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
    private fun showSuccessDialog(email: String) {
        // Using 'this@MainActivity' to get the Context within an Activity
        AlertDialog.Builder(this).apply {
            setTitle("Yeah!")
            setMessage("Akun dengan $email sudah jadi nih. Yuk, login.")
            setPositiveButton("Lanjut") { _, _ ->
                finish()
            }
            create()
            show()
        }
    }

    private fun validate(username: String, telp: String, password: String, confirm: String): Boolean {
        var valid = true;

        if(username == ""){
            binding.username.error = "Username harus diisi"
            valid = false;
        } else {
            binding.username.error = null;
        }

        if(telp == ""){
            binding.telp.error = "Nomer telpon harus diisi"
            valid = false;
        } else {
            binding.telp.error = null;
        }

        if(password == ""){
            binding.password.error = "Password harus diisi"
            valid = false;
        } else {
            binding.password.error = null;
        }

        if(confirm == ""){
            binding.confirm.error = "Password harus diisi"
            valid = false;
        } else {
            binding.password.error = null;

            if(password != confirm){
                binding.confirm.error = "Password tidak sesuai"
                valid = false;
            } else {
                binding.confirm.error = null;
            }
        }

        return valid;
    }
}