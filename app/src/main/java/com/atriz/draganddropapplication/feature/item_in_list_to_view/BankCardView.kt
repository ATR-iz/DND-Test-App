package com.atriz.draganddropapplication.feature.item_in_list_to_view

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.atriz.draganddropapplication.core.data.BankCard
import com.atriz.draganddropapplication.databinding.ViewBankCardBinding

class BankCardView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewBankCardBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.bankCardNumber.setOnDragListener { _, dragEvent ->
            if (dragEvent.action == DragEvent.ACTION_DRAG_LOCATION) {
                setBankCard(dragEvent.localState as BankCard)
            }
            true
        }
        binding.bankCardDate.setOnDragListener { _, dragEvent ->
            if (dragEvent.action == DragEvent.ACTION_DRAG_LOCATION) {
                setBankCard(dragEvent.localState as BankCard)
            }
            true
        }
        binding.bankCardCvv.setOnDragListener { _, dragEvent ->
            if (dragEvent.action == DragEvent.ACTION_DRAG_LOCATION) {
                setBankCard(dragEvent.localState as BankCard)
            }
            true
        }

        binding.root.setOnLongClickListener { view ->
            val bankCard = BankCard(
                binding.bankCardNumber.text.toString(),
                binding.bankCardDate.text.toString(),
                binding.bankCardCvv.text.toString(),
            )

            val myShadow = DragShadowBuilder(view)
            view.startDragAndDrop(null, myShadow, bankCard, 0)
            true
        }

        binding.root.setOnDragListener(ViewDragListener())
    }

    fun setBankCard(bankCard: BankCard) {
        binding.bankCardNumber.setText(bankCard.number)
        binding.bankCardDate.setText(bankCard.date)
        binding.bankCardCvv.setText(bankCard.cvv)
    }
}