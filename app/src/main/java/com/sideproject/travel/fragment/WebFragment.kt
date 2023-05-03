package com.sideproject.travel.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.sideproject.travel.MainActivity
import com.sideproject.travel.MainViewModel
import com.sideproject.travel.R
import com.sideproject.travel.databinding.FragmentWebBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects


@AndroidEntryPoint
class WebFragment : Fragment(R.layout.fragment_web) {

    private lateinit var binding: FragmentWebBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebBinding.bind(view)
        setToolbar()
        bindWebView()
    }

    private fun setToolbar() {
        (activity as MainActivity).apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="網頁"
        }
    }

    private fun bindWebView() {
        mainViewModel.data?.let {
            binding.webView.loadUrl(it.url)
            binding.webView.webViewClient=object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar.isVisible=false
                }
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.progressBar.isVisible=true
                }
            }
        }

    }
}