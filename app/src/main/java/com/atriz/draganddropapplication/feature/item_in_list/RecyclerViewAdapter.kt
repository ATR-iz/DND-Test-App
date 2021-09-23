package com.atriz.draganddropapplication.feature.item_in_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atriz.draganddropapplication.R
import com.atriz.draganddropapplication.core.data.BankCard
import java.util.*

class RecyclerViewAdapter(
    private val list: List<BankCard>,
) : RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_bank_card, viewGroup, false)
        return ItemHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ItemHolder, i: Int) {
        viewHolder.bind(list[i])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun moveItem(from: Int, to: Int): Boolean {
        if (from < to) {
            for (i in from until to) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in from downTo to + 1) {
                Collections.swap(list, i, i - 1)
            }
        }
        notifyItemMoved(from, to)
        return true
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardNumber: TextView = itemView.findViewById(R.id.card_number)

        fun bind(bankCard: BankCard) {
            cardNumber.text = bankCard.number
        }
    }
}