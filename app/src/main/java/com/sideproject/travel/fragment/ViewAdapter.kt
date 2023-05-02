package com.sideproject.travel.fragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sideproject.travel.databinding.ItemViewBinding
import com.sideproject.travel.model.Data

class ViewAdapter : RecyclerView.Adapter<ViewAdapter.ViewHolder>() {

    private var onItemClickLister: ((Data, Int) -> Unit)? = null
    private var values: List<Data> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    fun setData(values: List<Data>) {
        this.values = values
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
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

    override fun getItemCount(): Int = values.size

    fun setOnItemClickLister(lister: (Data, Int) -> Unit) {
        onItemClickLister = lister
    }

    inner class ViewHolder(binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvName: TextView = binding.tvName
        val tvIntroduction: TextView = binding.tvIntroduction
        val ivView: ImageView = binding.ivView

    }

}