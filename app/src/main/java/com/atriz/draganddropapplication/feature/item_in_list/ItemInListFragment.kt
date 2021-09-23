package com.atriz.draganddropapplication.feature.item_in_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.atriz.draganddropapplication.core.utils.DataGenerator
import com.atriz.draganddropapplication.databinding.FragmentItemInListBinding

class ItemInListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentItemInListBinding.inflate(inflater)

        val list = ArrayList(DataGenerator.generateRandomList(9))

        val adapter = RecyclerViewAdapter(list)

        binding.list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.list.adapter = adapter

        val callback = ItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.list)

        //binding.list.setOnDragListener(SimpleListDragListener())

        return binding.root
    }
}