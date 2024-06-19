package com.bangkit.application.view.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.application.R
import com.bangkit.application.data.entity.History
import com.bangkit.application.databinding.ItemHistoryBinding

class InputAdapter(private val onItemClickCallback: OnItemClickCallback) : RecyclerView.Adapter<InputAdapter.HistoryViewHolder>() {

    var listHistory = ArrayList<History>()
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listHistory.clear()
            }
            this.listHistory.addAll(listNotes)
        }

    fun addItem(history: History) {
        this.listHistory.add(history)
        notifyItemInserted(this.listHistory.size - 1)
    }

    fun updateItem(position: Int, history: History) {
        this.listHistory[position] = history
        notifyItemChanged(position, history)
    }

    fun removeItem(position: Int) {
        this.listHistory.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listHistory.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(listHistory[position])
    }

    override fun getItemCount(): Int = this.listHistory.size

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHistoryBinding.bind(itemView)
        fun bind(history: History) {
            binding.descText.text = history.expenses
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(selectedNote: History?, position: Int?)
    }
}