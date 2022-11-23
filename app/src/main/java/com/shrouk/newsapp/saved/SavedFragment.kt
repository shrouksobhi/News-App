package com.shrouk.newsapp.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.shrouk.newsapp.R
import com.shrouk.newsapp.adapter.NewsRecyclerViewAdapter
import com.shrouk.newsapp.databinding.FragmentSavedBinding
import com.shrouk.newsapp.interfaces.NewsOnClick
import com.shrouk.newsapp.model.Articles
import com.shrouk.newsapp.model.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SavedFragment : Fragment() ,NewsOnClick {
private lateinit var binding: FragmentSavedBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var skeleton: Skeleton

    private val savedViewModel:SavedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_saved, container, false)
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_saved ,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=binding.recyclviewid
        skeleton=recyclerView.applySkeleton(R.layout.home_recycler_itemlist)

        savedViewModel.getSavedNews()
        observeResponse()
    }

    private fun observeResponse() {
savedViewModel.news.observe(viewLifecycleOwner,{
  it.let {

      var datalist= ArrayList(it)
            installViews(datalist)
        }

})
    }
    private fun installViews(list :ArrayList<Articles>) {
        skeleton.showOriginal()
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        var newsRecyclerViewAdapter= NewsRecyclerViewAdapter(list,requireContext(),this)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter=newsRecyclerViewAdapter

    }

    override fun newsOnclick(news: Articles) {
    }

}