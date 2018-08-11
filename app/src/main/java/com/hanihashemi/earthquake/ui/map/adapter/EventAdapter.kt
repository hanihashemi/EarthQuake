package com.hanihashemi.earthquake.ui.map.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanihashemi.earthquake.databinding.ListItemFeatureBinding
import com.hanihashemi.earthquake.model.Feature

class EventAdapter : ListAdapter<Feature, EventAdapter.ViewHolder>(FeatureDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feature = getItem(position)
        holder.apply {
            bind(createOnClickListener(feature.id), feature)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemFeatureBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(featureId: String): View.OnClickListener {
        return View.OnClickListener {
        }
    }

    class ViewHolder(
            private val binding: ListItemFeatureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Feature) {
            binding.apply {
                clickListener = listener
                feature = item
                executePendingBindings()
            }
        }
    }
}