package com.thiagosantos.cardcomrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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


        rvHorizontalAdapter = RvHorizontalAdapter(context, SalonList[position].categoryItemList)

        recyclerView = holder.itemRecycler

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = rvHorizontalAdapter


        holder.categoryTitle.text = SalonList[position].categoryTitle
        holder.address.text = SalonList[position].address
        holder.note.text = SalonList[position].note.toString()
        holder.evaluation.text = SalonList[position].evaluation.toString()
0

        holder.bind(SalonList[position])

        rvHorizontalAdapter.onItemClick = {

           holder.click(SalonList[position].categoryItemList[it])

        }
    }

    override fun getItemCount(): Int {
        return SalonList.size
    }
}

class MainViewHolder(binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

    private val itemImage = binding.ivCardImage
    val itemRecycler = binding.rvHorizontal
    val categoryTitle = binding.tvNomeEstabelecimento
    val address =  binding.tvEndereco
    val note =  binding.nota
    val evaluation = binding.qtAvaliacoes


    fun bind(salon: Salon) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(salon.image)
            .transition(DrawableTransitionOptions.withCrossFade(2000))
            .into(itemImage)
    }

    fun click(item: ImageItem) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(item.imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade(2000))
            .into(itemImage)
    }
}


