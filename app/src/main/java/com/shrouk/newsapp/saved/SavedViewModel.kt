package com.shrouk.newsapp.saved

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shrouk.newsapp.dataSource.Repository
import com.shrouk.newsapp.model.Articles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(var repository: Repository) : ViewModel() {


    // MediatorLiveData used to combine multi livedata sources in one livedata
    var news = MediatorLiveData<List<Articles>>()

    fun getSavedNews() {
        viewModelScope.launch {
            news.addSource(repository.getSavedNews()) {
                news.value = it

            }


        }
    }
}