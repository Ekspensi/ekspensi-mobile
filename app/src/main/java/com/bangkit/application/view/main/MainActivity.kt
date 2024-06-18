package com.bangkit.application.view.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.application.R
import com.bangkit.application.databinding.ActivityMainBinding
import com.bangkit.application.view.ViewModelFactory
import com.bangkit.application.view.login.LoginActivity
import com.bangkit.application.view.main.adapter.ExpensesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getSession().observe(this) {
            Log.d("usermodel", it.toString())
            if (!it.isLogin){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        setupView()
        setupAction()
    }

    private fun setupView(){
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
        val layoutManager = LinearLayoutManager(this)
        binding.rvExpenses.layoutManager = layoutManager

        val adapter = ExpensesAdapter()
        binding.rvExpenses.adapter = adapter

        viewModel.listData.observe(this) {
            adapter.submitData(lifecycle, it)
        }
//        viewModel.listData.observe(this){
//            Log.d("response", it.toString())
//            if (it.statusCode == 200){
//                it.data?.let { data -> setExpensesData(data) }
//            } else {
//                //TODO: refactor variable naming
//                it.message?.let { it1 ->
//                    Log.d("action", "setupAction: masuk sini")
//                    showErrorDialog(it1) }
//            }
//        }

        Log.d("action", "setupAction: triggered")

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            Log.d("action", "setupAction: clicked")
            when (menuItem.itemId) {
                R.id.menu1 -> {
                    viewModel.logout()
                    true
                }
                else -> false
            }
        }
    }


    private fun showErrorDialog(errorMessage: String) {
        // Using 'this@MainActivity' to get the Context within an Activity
        Log.d("action", "showErrorDialog: called")
        AlertDialog.Builder(this).apply {
            setTitle("Error")
            setMessage(errorMessage)
            setPositiveButton("OK", null)
            create()
            show()
        }
    }
}