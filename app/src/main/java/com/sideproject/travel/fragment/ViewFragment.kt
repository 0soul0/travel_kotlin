package com.sideproject.travel.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sideproject.travel.MainViewModel
import com.sideproject.travel.R
import com.sideproject.travel.databinding.FragmentViewListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewFragment : Fragment(R.layout.fragment_view_list) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentViewListBinding

    private val viewAdapter by lazy {
        ViewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewListBinding.bind(view)
        initRecycleView()
        observer()
    }

    private fun observer() {
        mainViewModel.queryViews("zh-tw", 1).observe(requireActivity()) {
            viewAdapter.setData(it)
        }
    }


    private fun initRecycleView() {
        binding.rvView.apply {
            adapter = viewAdapter
            viewAdapter.setOnItemClickLister { data, i ->
                mainViewModel.data=data
                findNavController().navigate(R.id.detailFragment)
            }
        }
    }
}