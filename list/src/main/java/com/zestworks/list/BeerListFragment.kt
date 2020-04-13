package com.zestworks.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.zestworks.common.LCE
import org.koin.androidx.viewmodel.ext.android.viewModel

class BeerListFragment : Fragment(R.layout.fragment_beer_list) {
    private val beerListViewModel: BeerListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beerListViewModel.onUILoad()
        beerListViewModel.beerListState.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("test", it.toString())
                when (it) {
                    LCE.Loading -> {

                    }
                    is LCE.Content -> {

                    }
                    is LCE.Error -> {

                    }
                }
            }
        })
    }
}