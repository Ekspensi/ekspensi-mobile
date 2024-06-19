package com.bangkit.application.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.application.data.remote.response.DataItem
import com.bangkit.application.databinding.ItemExpensesBinding
import com.bangkit.application.utils.convertToDateOnly
import com.bangkit.application.utils.formatToRupiah

class ExpensesAdapter: PagingDataAdapter<DataItem, ExpensesAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemExpensesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val review = getItem(position)
        review?.let {holder.bind(it)}
    }

    class MyViewHolder(val binding: ItemExpensesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(expenses: DataItem){
            binding.klasifikasiText.text = expenses.klasifikasi
            binding.datetimeText.text = expenses.createdAt?.let { convertToDateOnly(it) }
            binding.nominalText.text = expenses.nominal?.let { formatToRupiah(it) }
            binding.descText.text = expenses.deskripsi
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}