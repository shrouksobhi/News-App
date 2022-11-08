package com.shrouk.newsapp.search

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
class SearchViewModel @Inject constructor(var repository: Repository): ViewModel(){
    private var _currentSearch= MutableLiveData<Resource<ArrayList<Articles>>>()
    var currentSearch: LiveData<Resource<ArrayList<Articles>>> = _currentSearch
    fun getSearch(query:String){
        viewModelScope.launch {
            val search=repository.getSearch(query)
            _currentSearch.value= search
        }
    }
}