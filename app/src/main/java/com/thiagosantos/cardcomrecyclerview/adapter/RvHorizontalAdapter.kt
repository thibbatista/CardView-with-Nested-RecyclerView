package com.thiagosantos.cardcomrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thiagosantos.cardcomrecyclerview.R
import com.thiagosantos.cardcomrecyclerview.databinding.ImageBinding
import com.thiagosantos.cardcomrecyclerview.model.ImageItem

class RvHorizontalAdapter(
    private val context: Context,
    private val imageItemList: List<ImageItem>
) :
    RecyclerView.Adapter<RvHorizontalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHorizontalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageBinding.inflate(inflater, parent, false)
        return RvHorizontalViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RvHorizontalViewHolder, position: Int) {
        holder.bind(imageItemList[position])
    }

    override fun getItemCount(): Int {
        return imageItemList.size
    }

}

class RvHorizontalViewHolder(binding: ImageBinding) : RecyclerView.ViewHolder(binding.root) {

    private val itemImage: ImageView = binding.imageView

    fun bind(imageItem: ImageItem) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageItem.imageUrl)
            .into(itemImage)
    }
}