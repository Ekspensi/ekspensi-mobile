package com.bangkit.application.view.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.application.data.remote.response.DataExpenses
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
        viewModel.listData.observe(this){
            if (it.statusCode == 200){
                it.data?.let { data -> setExpensesData(data) }
            } else {
                //TODO: refactor variable naming
                it.message?.let { it1 -> showErrorDialog(it1) }
            }
        }
    }

    private fun setExpensesData(expenses: List<DataExpenses?>){
        val adapter = ExpensesAdapter()
        adapter.submitList(expenses)
        binding.rvExpenses.adapter = adapter
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
}