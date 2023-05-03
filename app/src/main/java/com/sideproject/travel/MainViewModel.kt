package com.sideproject.travel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sideproject.travel.model.Data
import com.sideproject.travel.repo.TravelRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val travelRepo: TravelRepo
) : ViewModel() {

    var data:Data?=null
    fun queryViews(): Flow<PagingData<Data>> =
        travelRepo.queryViews()

}