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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.application.R
import com.bangkit.application.data.remote.response.LoginResponse
import com.bangkit.application.databinding.ActivityMainBinding
import com.bangkit.application.view.ViewModelFactory
import com.bangkit.application.view.history.HistoryActivity
import com.bangkit.application.view.login.LoginActivity
import com.bangkit.application.view.main.adapter.ExpensesAdapter
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        lifecycleScope.launch {
            try {
                val response = viewModel.overview()
                response.data?.let {data ->
                    data.freqTrxCurrentMonth?.let { freq ->
                        binding.currTrans.setText("Transactions: ${freq}")
                    }

                    data.volTrxCurrentMonth?.let {vol ->
                        binding.currAmount.setText("Amount: ${vol}")
                    }

                    data.freqTrxPrevMonth?.let { freq ->
                        binding.prevTrans.setText("Transactions: ${freq}")
                    }

                    data.volTrxPrevMonth?.let {vol ->
                        binding.prevAmount.setText("Amount: ${vol}")
                    }

                    data.expensesGrowth?.let { growth ->
                        binding.sumAmount.setText("Growth: ${growth}")
                    }

                    data.expensesGrowthPercentage?.let {percen ->
                        binding.sumPercen.setText("Percentage: ${percen}")
                    }
                }
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
                val errorMessage = errorBody.message
                errorMessage?.let {showErrorDialog(it)}
            }
        }

        viewModel.listData.observe(this) {
            Log.d("paging data", this.toString())
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
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
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