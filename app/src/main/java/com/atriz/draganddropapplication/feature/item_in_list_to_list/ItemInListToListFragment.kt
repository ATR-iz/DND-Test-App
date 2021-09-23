package com.atriz.draganddropapplication.feature.item_in_list_to_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.atriz.draganddropapplication.core.ui.RecyclerViewAdapter
import com.atriz.draganddropapplication.core.utils.DataGenerator
import com.atriz.draganddropapplication.databinding.FragmentItemInListToListBinding

class ItemInListToListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemInListToListBinding.inflate(inflater)

        val listLeft = ArrayList(DataGenerator.generateRandomList(5))
        val listRight = ArrayList(DataGenerator.generateRandomList(5))

        binding.listLeft.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.listLeft.adapter = RecyclerViewAdapter(listLeft)

        binding.listRight.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.listRight.adapter = RecyclerViewAdapter(listRight)

        val viewHolder = RecyclerViewHolder()

        binding.listLeft.setOnDragListener(ListDragListener(viewHolder))
        binding.listRight.setOnDragListener(ListDragListener(viewHolder))

        return binding.root
    }
}