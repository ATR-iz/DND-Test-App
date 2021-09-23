package com.atriz.draganddropapplication.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.atriz.draganddropapplication.core.DragShadowBuilder
import com.atriz.draganddropapplication.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMainBinding.inflate(inflater)

        binding.buttonItemInList.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.toItemInListFragment())
        }

        binding.buttonItemInList.setOnLongClickListener {
            it.startDragAndDrop(null, DragShadowBuilder(it), null, 0)
        }


        binding.buttonItemInListToList.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.toItemInListToListFragment())
        }

        binding.buttonItemInListToView.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.toItemInListToViewFragment())
        }

        return binding.root
    }
}