package com.zestworks.beerdetail.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.zestworks.beerdetail.R
import com.zestworks.common.LCE.Content
import com.zestworks.common.LCE.Error
import com.zestworks.common.LCE.Loading
import kotlinx.android.synthetic.main.fragment_beer_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerDetailFragment : Fragment(R.layout.fragment_beer_detail) {
    private val viewModel: BeerDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.beerDetailStream.observe(viewLifecycleOwner, Observer {
            when (it) {
                Loading -> {
                    Toast.makeText(context, "Loading..", Toast.LENGTH_SHORT).show()
                }
                is Content -> {
                    Glide.with(this).load(it.data.imageURL).into(detailImage)
                    detailName.text = it.data.name
                    detailDescription.text = it.data.description
                    detailAbv.text = it.data.abv
                    detailHops.text = it.data.hops
                    detailMethods.text = it.data.methods
                    detailMalts.text = it.data.malts
                }
                is Error -> {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
        val beerID = arguments!!["beerID"].toString().toInt()
        viewModel.onUILoaded(beerID)
    }
}