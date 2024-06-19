package com.bangkit.application.view.history

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.application.R
import com.bangkit.application.data.db.HistoryHelper
import com.bangkit.application.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyHelper: HistoryHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historyHelper = HistoryHelper.getInstance(applicationContext)
        historyHelper.open()
    }

    override fun onClick(view: View) {
        TODO("Not yet implemented")
    }
}