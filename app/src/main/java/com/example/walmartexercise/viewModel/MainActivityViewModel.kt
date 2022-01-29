package com.example.walmartexercise.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartexercise.model.WalmartProductsApiResponse
import com.example.walmartexercise.repository.MainActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<WalmartProductsApiResponse>? = null

    fun getUser() : LiveData<WalmartProductsApiResponse>? {
        viewModelScope.launch(Dispatchers.IO) {
            servicesLiveData = MainActivityRepository.getServicesApiCall()
        }
return servicesLiveData
    }

}