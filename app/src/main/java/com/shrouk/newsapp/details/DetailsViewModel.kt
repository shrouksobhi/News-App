package com.shrouk.newsapp.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrouk.newsapp.dataSource.Repository
import com.shrouk.newsapp.model.Articles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(var repository: Repository) :ViewModel() {

    fun saveNews(articles: Articles){
        viewModelScope.launch {
            repository.saveNews(articles)
        }
    }


}