package com.shrouk.newsapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrouk.cv.model.Resource
import com.shrouk.newsapp.dataSource.Repository
import com.shrouk.newsapp.model.Articles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var repository: Repository) :ViewModel() {

  private var _currentHeadlines=MutableLiveData<Resource<java.util.ArrayList<Articles>>>()
          var currentHeadlines:LiveData<Resource<java.util.ArrayList<Articles>>> = _currentHeadlines
    fun getHeadlines(){
        viewModelScope.launch {
            val headlines=repository.getHeadlines()
            _currentHeadlines.value= headlines
        }
    }
    private var _currentEverything= MutableLiveData<Resource<ArrayList<Articles>>>()
    var currentEverything: LiveData<Resource<ArrayList<Articles>>> = _currentEverything
    fun getEverything(){
        viewModelScope.launch {
            val everything=repository.getEverything()
            _currentEverything.value= everything
        }
    }


}