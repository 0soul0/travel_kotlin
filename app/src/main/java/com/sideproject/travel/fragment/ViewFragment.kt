package com.sideproject.travel.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sideproject.travel.MainActivity
import com.sideproject.travel.MainViewModel
import com.sideproject.travel.R
import com.sideproject.travel.databinding.FragmentViewListBinding
import com.sideproject.travel.hilt.Data
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ViewFragment : Fragment(R.layout.fragment_view_list), MenuProvider {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentViewListBinding

    private val viewAdapter by lazy {
        ViewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewListBinding.bind(view)

        setToolbar()
        initRecycleView()
        observer()
    }

    private fun setToolbar() {

        (activity as MainActivity).apply {
            supportActionBar?.title="景點"
            addMenuProvider(this@ViewFragment, viewLifecycleOwner, Lifecycle.State.RESUMED)
        }

    }

    private fun observer() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.queryViews(mainViewModel.language).collectLatest {
                    viewAdapter.submitData(it)
                }
            }
        }
    }

    private fun initRecycleView() {
        binding.rvView.apply {
            adapter = viewAdapter
            viewAdapter.setOnItemClickLister { data, _ ->
                mainViewModel.data=data
                findNavController().navigate(R.id.detailFragment)
            }
        }
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.item_language ->dialog()
        }
        return false
    }

    private fun dialog() {
        Dialog(requireContext()).also { dialog->
            dialog.setContentView(R.layout.dialog_language)
            dialog.findViewById<RecyclerView>(R.id.rv_language).also {
                it.adapter = DialogAdapter(Data.language).apply {
                    setOnItemClickLister { language, _ ->
                        mainViewModel.queryViews(language.value)
                        dialog.dismiss()
                    }
                }
            }
        }.show()
    }
}