package com.atriz.draganddropapplication.feature.item_in_list_to_view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.atriz.draganddropapplication.core.ui.RecyclerViewAdapter
import com.atriz.draganddropapplication.core.utils.DataGenerator
import com.atriz.draganddropapplication.databinding.FragmentItemInListToViewBinding

class ItemInListToViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemInListToViewBinding.inflate(inflater)

        val list = ArrayList(DataGenerator.generateRandomList(5))

        val adapter = RecyclerViewAdapter(list)

        binding.list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.list.adapter = adapter

        binding.list.setOnDragListener(ListDragListener())

        return binding.root
    }
}