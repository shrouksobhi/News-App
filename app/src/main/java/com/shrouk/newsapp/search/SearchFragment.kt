package com.shrouk.newsapp.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.shrouk.newsapp.R
import com.shrouk.newsapp.adapter.HomeViewPagerAdapter
import com.shrouk.newsapp.adapter.NewsRecyclerViewAdapter
import com.shrouk.newsapp.databinding.FragmentSearchBinding
import com.shrouk.newsapp.home.HomeViewModel
import com.shrouk.newsapp.model.Articles
import com.shrouk.newsapp.model.Status
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SearchFragment : Fragment() {
 private lateinit var binding:FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var skeleton: Skeleton
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    // return inflater.inflate(R.layout.fragment_search, container, false)
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.recyclviewid
        var queryText=binding.query

        skeleton = recyclerView.applySkeleton(R.layout.home_recycler_itemlist)
        skeleton.showSkeleton()
        queryText.setOnEditorActionListener { query, actionId, event ->
            if(actionId==EditorInfo.IME_ACTION_SEARCH){

                var query=queryText.text.toString()
                searchViewModel.getSearch(query)
                observeSearchResponse()
            }
            return@setOnEditorActionListener false
        }
    }

    private fun observeSearchResponse() {
searchViewModel.currentSearch.observe(viewLifecycleOwner,{
    when (it.status) {
        Status.OK-> {
            var datalist=ArrayList<Articles>()
            datalist = it.articles!!
            installViews(datalist)
        }
        Status.LOADING -> {
            skeleton.showSkeleton()

        }
        Status.ERROR -> {
            Toast.makeText(requireContext(),""+it.totalResults, Toast.LENGTH_SHORT).show()
        }
    }
})    }
    private fun installViews(list :ArrayList<Articles>) {
        skeleton.showOriginal()
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        var newsRecyclerViewAdapter= NewsRecyclerViewAdapter(list,requireContext())
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=newsRecyclerViewAdapter

    }

}