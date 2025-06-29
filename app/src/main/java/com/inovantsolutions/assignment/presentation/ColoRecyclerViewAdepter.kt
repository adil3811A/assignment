package com.inovantsolutions.assignment.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inovantsolutions.assignment.databinding.ColorImageViewBinding

class ColoRecyclerViewAdepter(
    private val context: Context,
    private val images: List<String>
) : RecyclerView.Adapter<ColoRecyclerViewAdepter.ColorViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorViewHolder {
        val view = ColorImageViewBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ColorViewHolder,
        position: Int
    ) {
        if (position==0 || position%2==0){
            Glide.with(context).load(images[position]).into(holder.imageview)
        }
    }

    override fun getItemCount(): Int = images.size


    inner class ColorViewHolder(colorImageViewBinding: ColorImageViewBinding): RecyclerView.ViewHolder(colorImageViewBinding.root){
        val imageview = colorImageViewBinding.imageView
    }
}