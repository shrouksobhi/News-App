package com.shrouk.newsapp.home

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.shrouk.newsapp.R
import com.shrouk.newsapp.interfaces.NewsOnClick
import com.shrouk.newsapp.adapter.HomeViewPagerAdapter
import com.shrouk.newsapp.adapter.NewsRecyclerViewAdapter
import com.shrouk.newsapp.databinding.FragmentHomeBinding
import com.shrouk.newsapp.model.Articles
import com.shrouk.newsapp.model.Status
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() , NewsOnClick {

private lateinit var binding:FragmentHomeBinding
private val homeViewModel:HomeViewModel by viewModels()
    private lateinit var skeleton:Skeleton
    private lateinit var viewPager:ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
//        return inflater.inflate(R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         recyclerView=binding.recyclviewid
        viewPager=binding.viewPager
        dotsIndicator=binding.dotsIndicator
        homeViewModel.getHeadlines()
        observeHeadlinesResponse()
       skeleton=recyclerView.applySkeleton(R.layout.home_recycler_itemlist)
        homeViewModel.getEverything()
        observeEverythingResponse()

    }

    private fun observeHeadlinesResponse() {
        homeViewModel.currentHeadlines
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.OK -> {
                        val datalist= ArrayList<Articles>()
                        datalist.addAll(it.articles!!)
                        homeViewPagerAdapter = HomeViewPagerAdapter(datalist,requireContext())
                        viewPager.adapter = homeViewPagerAdapter
                        var currentPage = 0
                        val timer: Timer?
                        val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed

                        val PERIOD_MS: Long = 4000 // time in milliseconds between successive task executions.

                        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                        dotsIndicator.attachTo(viewPager)

                        val handler = Handler()
                        val update = Runnable {
                            if (currentPage == datalist.size) {
                                currentPage = 0
                            }
                            viewPager.setCurrentItem(currentPage++, true)
                        }

                        timer = Timer() // This will create a new Thread

                        timer.schedule(object : TimerTask() {
                            // task to be scheduled
                            override fun run() {
                                handler.post(update)
                            }
                        }, DELAY_MS, PERIOD_MS)
                    }
                    Status.LOADING -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), ""+it.totalResults, Toast.LENGTH_SHORT).show()

                    }
                }
            }
    }

    private fun observeEverythingResponse() {
homeViewModel.currentEverything.observe(viewLifecycleOwner,{
    when (it.status) {
        Status.OK-> {
            var datalist=java.util.ArrayList<Articles>()
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
})

    }

    private fun installViews(list :ArrayList<Articles>) {
        skeleton.showOriginal()
        var layoutManager:LayoutManager= LinearLayoutManager(requireContext())
        var newsRecyclerViewAdapter=NewsRecyclerViewAdapter(list,requireContext(),this)
      recyclerView.layoutManager=layoutManager
        recyclerView.adapter=newsRecyclerViewAdapter

    }

    override fun newsOnclick(news: Articles) {
   findNavController().navigate(HomeFragmentDirections.actionFragmentHomeToDetailsFragment(news))
    }

}