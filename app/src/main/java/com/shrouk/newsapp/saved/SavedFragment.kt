package com.shrouk.newsapp.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.shrouk.newsapp.R
import com.shrouk.newsapp.databinding.FragmentSavedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SavedFragment : Fragment() {
private lateinit var binding: FragmentSavedBinding

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
    }

}