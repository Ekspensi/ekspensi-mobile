package com.bangkit.application.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.application.data.remote.response.DataExpenses
import com.bangkit.application.databinding.ActivitySignupBinding
import com.bangkit.application.databinding.ItemExpensesBinding

class ExpensesAdapter: ListAdapter<DataExpenses, ExpensesAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemExpensesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        holder.bind(review)
    }

    class MyViewHolder(val binding: ItemExpensesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(expenses: DataExpenses){
            binding.klasifikasiText.text = expenses.klasifikasi
            binding.datetimeText.text = expenses.dateTime
            binding.nominalText.text = expenses.nominal.toString()
            binding.descText.text = expenses.deskripsi
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataExpenses>() {
            override fun areItemsTheSame(oldItem: DataExpenses, newItem: DataExpenses): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataExpenses, newItem: DataExpenses): Boolean {
                return oldItem == newItem
            }
        }
    }
}