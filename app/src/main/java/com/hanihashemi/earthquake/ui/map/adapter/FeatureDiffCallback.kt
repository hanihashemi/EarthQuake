package com.hanihashemi.earthquake.ui.map.adapter

import android.support.v7.util.DiffUtil
import com.hanihashemi.earthquake.model.Feature

class FeatureDiffCallback : DiffUtil.ItemCallback<Feature>() {

    override fun areItemsTheSame(oldItem: Feature, newItem: Feature): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Feature, newItem: Feature): Boolean {
        return oldItem != newItem
    }
}