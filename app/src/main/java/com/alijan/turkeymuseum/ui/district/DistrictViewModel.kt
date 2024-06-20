package com.alijan.turkeymuseum.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.turkeymuseum.data.model.District
import com.alijan.turkeymuseum.data.repository.MuseumRepository
import com.alijan.turkeymuseum.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DistrictViewModel @Inject constructor(private val museumRepository: MuseumRepository) :
    ViewModel() {

    private var _districts = MutableLiveData<NetworkResponse<List<District>>>()
    val district: LiveData<NetworkResponse<List<District>>> get() = _districts

    fun getAllDistrict(city: String) {
        viewModelScope.launch {
            try {
                _districts.value = NetworkResponse.Loading()
                val resp = museumRepository.getAllDistrict(city)
                if (resp.isSuccessful) {
                    resp.body()?.let {
                        _districts.value = NetworkResponse.Success(it.data)
                    }
                } else {
                    _districts.value = NetworkResponse.Error(resp.errorBody().toString())
                }
            } catch (e: Exception) {
                _districts.value = NetworkResponse.Error(e.localizedMessage?.toString())
            }
        }
    }

}