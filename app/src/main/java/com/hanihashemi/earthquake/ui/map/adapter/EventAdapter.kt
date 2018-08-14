package com.hanihashemi.earthquake.ui.map.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanihashemi.earthquake.GlideApp
import com.hanihashemi.earthquake.R
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
                GlideApp.with(root.context)
                        .load("https://www.countryflags.io/${item.properties.countryCode}/flat/64.png")
                        .placeholder(ColorDrawable(Color.GRAY))
                        .fitCenter()
                        .into(imgFlag)

                val time = DateUtils.getRelativeDateTimeString(
                        root.context,
                        item.properties.time,
                        DateUtils.SECOND_IN_MILLIS,
                        DateUtils.WEEK_IN_MILLIS,
                        DateUtils.FORMAT_ABBREV_RELATIVE)

                txtDate.text = time.substring(0, time.lastIndexOf(","))
                txtMag.setTextColor(ContextCompat.getColor(root.context, chooseMagColor(item.properties.mag)))
                executePendingBindings()
            }
        }

        fun chooseMagColor(value: Float) =
                when {
                    value <= 3.5F -> R.color.alertLevel1
                    value <= 5F -> R.color.alertLevel2
                    value <= 6.5F -> R.color.alertLevel3
                    else -> R.color.alertLevel4
                }

    }
}