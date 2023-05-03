package com.sideproject.travel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sideproject.travel.model.Data
import com.sideproject.travel.repo.TravelRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val travelRepo: TravelRepo
) : ViewModel() {

    private var _views = MutableLiveData<List<Data>>()
    private var views: LiveData<List<Data>> = _views

    var data:Data?=null
    var language:String = "zh-tw"

    fun queryViews(language: String, page: Int): LiveData<List<Data>> {
        this.language=language
        viewModelScope.launch(Dispatchers.IO) {
            _views.postValue(travelRepo.queryViews(language, page.toString()))
        }
        return views
    }

}