package com.atriz.draganddropapplication.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atriz.draganddropapplication.R
import com.atriz.draganddropapplication.core.DragShadowBuilder
import com.atriz.draganddropapplication.core.data.BankCard
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter(
    private val arrayList: ArrayList<BankCard>,
) : RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_bank_card, viewGroup, false)
        return ItemHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ItemHolder, i: Int) {
        viewHolder.bind(arrayList[i])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun contains(bankCard: BankCard): Boolean {
        return arrayList.contains(bankCard)
    }

    fun getItemPosition(bankCard: BankCard): Int {
        return arrayList.indexOf(bankCard)
    }

    fun addItem(bankCard: BankCard) {
        arrayList.add(bankCard)
        notifyItemInserted(arrayList.lastIndex)
    }

    fun addItem(bankCard: BankCard, position: Int) {
        arrayList.add(position, bankCard)
        notifyItemInserted(position)
    }

    fun moveItem(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(arrayList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(arrayList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    fun removeItem(bankCard: BankCard) {
        val position = getItemPosition(bankCard)
        if (position != -1) {
            arrayList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardNumber: TextView = itemView.findViewById(R.id.card_number)

        fun bind(bankCard: BankCard) {
            cardNumber.text = bankCard.number

            itemView.setOnLongClickListener {
                val myShadow = DragShadowBuilder(itemView)
                itemView.startDragAndDrop(null, myShadow, bankCard, 0)

                true
            }
        }
    }
}