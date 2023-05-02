package com.sideproject.travel.fragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sideproject.travel.databinding.ItemLanguageBinding
import com.sideproject.travel.databinding.ItemViewBinding
import com.sideproject.travel.model.Data
import com.sideproject.travel.model.Language

class DialogAdapter(private var values: List<Language>) :
    RecyclerView.Adapter<DialogAdapter.ViewHolder>() {

    private var onItemClickLister: ((Language, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemLanguageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.run {
            tvLanguage.text = item.name
            itemView.setOnClickListener {
                onItemClickLister?.let {
                    it(item, position)
                }
            }
        }

    }

    override fun getItemCount(): Int = values.size

    fun setOnItemClickLister(lister: (Language, Int) -> Unit) {
        onItemClickLister = lister
    }

    inner class ViewHolder(binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvLanguage: TextView = binding.tvLanguage

    }

}