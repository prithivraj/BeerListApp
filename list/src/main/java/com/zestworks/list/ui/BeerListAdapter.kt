package com.zestworks.list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zestworks.list.R
import com.zestworks.list.databinding.BeerListItemBinding

internal class BeerListAdapter(private var items: List<Beer>) :
    RecyclerView.Adapter<BeerListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        return BeerListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.beer_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val beer = items[position]
        holder.binding.apply {
            beerName.text = beer.name
            beerAbv.text = beer.abv.toString()
            beerType.text = beer.beerType.name
            Glide.with(holder.itemView.context).load(beer.imageUrl).into(beerImage)
        }
    }

    fun setData(listOfBeers: List<Beer>) {
        items = listOfBeers
    }
}

class BeerListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = BeerListItemBinding.bind(itemView)
}