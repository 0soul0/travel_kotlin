package com.sideproject.travel.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sideproject.travel.databinding.ItemViewBinding
import com.sideproject.travel.model.Data

class ViewAdapter : PagingDataAdapter<Data, ViewAdapter.ViewHolder>(ARTICLE_DIFF_CALLBACK) {

    private var onItemClickLister: ((Data, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.run {
            Glide.with(holder.itemView).load(
                if (item.images.isNotEmpty())
                    item.images[0].src
                else
                    ""
            ).centerCrop().into(ivView)
            tvName.text = item.name
            tvIntroduction.text = item.introduction
            itemView.setOnClickListener {
                onItemClickLister?.let {
                    it(item, position)
                }
            }
        }

    }


    fun setOnItemClickLister(lister: (Data, Int) -> Unit) {
        onItemClickLister = lister
    }

    inner class ViewHolder(binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvName: TextView = binding.tvName
        val tvIntroduction: TextView = binding.tvIntroduction
        val ivView: ImageView = binding.ivView

    }


    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem == newItem
        }
    }
}