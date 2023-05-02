package com.sideproject.travel.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sideproject.travel.MainViewModel
import com.sideproject.travel.R
import com.sideproject.travel.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        bindData()
    }

    private fun bindData() {
        mainViewModel.data?.let {
            Glide.with(this).load(
                if (it.images.isNotEmpty())
                    it.images[0].src
                else
                    ""
            ).centerCrop().into(binding.ivView)
            binding.tvName.text = it.name
            binding.tvIntroduction.text = it.introduction
            binding.tvAddress.text = it.address
            binding.tvTime.text = it.modified
            binding.tvUrl.apply {
                text = it.url
                setOnClickListener {
                    findNavController().navigate(R.id.webFragment)
                }
            }
        }
    }

}