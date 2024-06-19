package com.bangkit.application.view.history

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.application.data.db.DatabaseContract
import com.bangkit.application.data.db.HistoryHelper
import com.bangkit.application.data.entity.History
import com.bangkit.application.databinding.ActivityHistoryBinding
import com.bangkit.application.helper.MappingHelper
import com.bangkit.application.view.ViewModelFactory
import com.bangkit.application.view.history.adapter.HistoryAdapter
import com.bangkit.application.view.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyHelper: HistoryHelper
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HistoryAdapter()
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(this)

        historyHelper = HistoryHelper.getInstance(applicationContext)
        historyHelper.open()

        if (savedInstanceState == null) {
            loadHistoryAsync()
        } else {
            val savedHistory = savedInstanceState.getParcelableArrayList<History>(EXTRA_STATE)
            if (savedHistory != null) {
                adapter.listHistory = savedHistory
            }
        }

        setupAction()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listHistory)
    }

    private fun setupAction() {
        binding.addButton.setOnClickListener {
            val description = binding.historyInput.text.toString().trim()

            if (description.isEmpty()) {
                binding.textField.error = "Form harus diisi"
            } else {
                binding.textField.error = null

                val factory: ViewModelFactory = ViewModelFactory.getInstance(this)

                val viewModel: HistoryViewModel by viewModels {
                    factory
                }

                lifecycleScope.launch {
                    try {
                        val response = viewModel.
                    }
                }

                val values = ContentValues().apply {
                    put(DatabaseContract.HistoryColumn.EXPENSES, description)
                }
                val result = historyHelper.insert(values)

                if (result > 0) {
                    val history = History().apply {
                        expenses = description
                    }
                    showSuccessDialog()
                    adapter.addItem(history)
                    binding.rvHistory.smoothScrollToPosition(adapter.itemCount - 1)
                } else {
                    showErrorDialog("Gagal menambahkan pengeluaran, coba lagi.")
                }
            }
        }
    }

    private fun loadHistoryAsync() {
        lifecycleScope.launch {
            Log.d("HistoryActivity", "Loading history asynchronously")
            binding.progressBar.visibility = View.VISIBLE
            val deferredHistory = async(Dispatchers.IO) {
                Log.d("HistoryActivity", "Querying all history from database")
                val cursor = historyHelper.queryAll()
                val histories = MappingHelper.mapCursorToArrayList(cursor)
                cursor.close()
                histories
            }
            val history = deferredHistory.await()
            binding.progressBar.visibility = View.GONE
            if (history.isNotEmpty()) {
                Log.d("HistoryActivity", "History loaded successfully: ${history.size} items")
                adapter.listHistory = history
            } else {
                Log.d("HistoryActivity", "No history found")
                adapter.listHistory = ArrayList()
            }
        }
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Yeah!")
            setMessage("Anda menambahkan pengeluaran")
            setPositiveButton("Lanjut", null)
            create()
            show()
        }
    }

    private fun showErrorDialog(errorMessage: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Error")
            setMessage(errorMessage)
            setPositiveButton("OK", null)
            create()
            show()
        }
    }

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }
}