package com.inovantsolutions.assignment.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inovantsolutions.assignment.R
import com.inovantsolutions.assignment.databinding.BannerImageItemBinding

class BannerImageViewPagerAdepter(val context: Context, val images: List<String>): RecyclerView.Adapter<BannerImageViewPagerAdepter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = BannerImageItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        Glide.with(context).load(images[position]).into(holder.imageView)
    }


    override fun getItemCount(): Int = images.size

    inner class ViewHolder(bannerImageItemBinding: BannerImageItemBinding): RecyclerView.ViewHolder(bannerImageItemBinding.root){
        val imageView = bannerImageItemBinding.bannerImage
    }

}