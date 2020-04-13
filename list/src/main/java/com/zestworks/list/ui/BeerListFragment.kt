package com.zestworks.list.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zestworks.common.LCE
import com.zestworks.list.R
import com.zestworks.list.ui.BeerListViewState.InvalidBeerListViewState
import com.zestworks.list.ui.BeerListViewState.ValidBeerListViewState
import kotlinx.android.synthetic.main.fragment_beer_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerListFragment : Fragment(R.layout.fragment_beer_list) {

    private val beerListViewModel: BeerListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beerListViewModel.onUILoad()
        beerListViewModel.beerListState.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    LCE.Loading -> {
                        Toast.makeText(context, "Loading..", Toast.LENGTH_SHORT)
                    }
                    is LCE.Content -> {
                        when (val data = it.data) {
                            is ValidBeerListViewState -> {
                                if (beerList.adapter != null) {
                                    (beerList.adapter as BeerListAdapter).setData(data.listOfBeers)
                                    (beerList.adapter as BeerListAdapter).notifyDataSetChanged()
                                } else {
                                    val beerListAdapter = BeerListAdapter(data.listOfBeers)
                                    beerList.adapter = beerListAdapter
                                    beerList.layoutManager = LinearLayoutManager(context)
                                }
                            }
                            InvalidBeerListViewState -> {
                                Toast.makeText(
                                    context,
                                    "Cannot create a queue with the given requirements",
                                    Toast.LENGTH_SHORT
                                )
                            }
                        }
                    }
                    is LCE.Error -> {
                        Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT)
                    }
                }
            }
        })
    }
}