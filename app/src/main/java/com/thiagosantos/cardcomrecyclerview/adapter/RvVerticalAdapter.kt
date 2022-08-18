package com.thiagosantos.cardcomrecyclerview.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.thiagosantos.cardcomrecyclerview.R
import com.thiagosantos.cardcomrecyclerview.databinding.ItemCardBinding
import com.thiagosantos.cardcomrecyclerview.model.ImageItem
import com.thiagosantos.cardcomrecyclerview.model.Salon

class RvVerticalAdapter(private val context: Context, private val SalonList: List<Salon>) :
    RecyclerView.Adapter<MainViewHolder>() {

    private lateinit var rvHorizontalAdapter: RvHorizontalAdapter
    private lateinit var recyclerView: RecyclerView




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {


        rvHorizontalAdapter = RvHorizontalAdapter(context,  SalonList[position].categoryItemList)

        recyclerView = holder.itemRecycler

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = rvHorizontalAdapter


        holder.categoryTitle.text = SalonList[position].categoryTitle

//        setCatItemRecycler(
//            holder.itemRecycler,
//            SalonList[position].categoryItemList
//        )

        holder.bind(SalonList[position].categoryItemList[0])

        rvHorizontalAdapter.onItemClick = {
            holder.bind(SalonList[position].categoryItemList[it])
            println("Usando Invoke e Unit para pega um Int entre classes $it")
        }


    }

    override fun getItemCount(): Int {
        return SalonList.size
    }

//
//    private fun setCatItemRecycler(
//        recyclerView: RecyclerView,
//        categoryItemList: List<ImageItem>
//    ) {
//
//        val itemRecyclerAdapter = RvHorizontalAdapter(context, categoryItemList)
//        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//        recyclerView.adapter = itemRecyclerAdapter
//
//    }

}

class MainViewHolder(binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

    private val itemImage = binding.ivCardImage
    val categoryTitle = binding.catTitle
    val itemRecycler = binding.rvHorizontal


    fun bind(imageItem: ImageItem) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageItem.imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemImage)
    }

}


