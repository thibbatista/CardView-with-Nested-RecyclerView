package com.thiagosantos.cardcomrecyclerview.adapter

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.thiagosantos.cardcomrecyclerview.R
import com.thiagosantos.cardcomrecyclerview.databinding.ImageBinding
import com.thiagosantos.cardcomrecyclerview.model.ImageItem
import com.thiagosantos.cardcomrecyclerview.model.Salon

class RvHorizontalAdapter(

    private val context: Context,
    private var imageItemList: List<ImageItem>,

) :
    RecyclerView.Adapter<RvHorizontalViewHolder>() {

    var onItemClick : ((Int) -> Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHorizontalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageBinding.inflate(inflater, parent, false)
        return RvHorizontalViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RvHorizontalViewHolder, position: Int) {
        val imageItem = imageItemList[position]
        holder.bind(imageItem)
        holder.itemView.setOnClickListener {

            onItemClick?.invoke(position)

        }

    }

    override fun getItemCount(): Int {
        return imageItemList.size
    }

}

class RvHorizontalViewHolder(binding: ImageBinding) : RecyclerView.ViewHolder(binding.root) {

    private val itemImage: ImageView = binding.imageView

    fun bind(imageItem: ImageItem) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageItem.imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade(2000))
            .into(itemImage)

    }
}