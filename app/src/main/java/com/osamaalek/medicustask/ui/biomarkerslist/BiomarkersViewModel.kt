package com.osamaalek.medicustask.ui.biomarkerslist

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.osamaalek.medicustask.data.BiomarkerRepository
import com.osamaalek.medicustask.utils.NetworkResult
import com.osamaalek.medicustask.model.Biomarker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiomarkersViewModel @Inject constructor(private val repository: BiomarkerRepository)
    : ViewModel()
{

    val isLoading = MutableLiveData(true)
    val isEmpty = MutableLiveData(true)
    private val errorMessage = MutableLiveData<Exception>()
    val _response: MutableLiveData<NetworkResult<List<Biomarker>>> = MutableLiveData()

    val response: LiveData<NetworkResult<List<Biomarker>>> = _response
    fun fetchBiomarkersResponse() = viewModelScope.launch{
        repository.getBiomarkers().onStart { isLoading.postValue(true) }
            .catch { e -> errorMessage.value = e as Exception }
            .onCompletion { isLoading.postValue(false) }
            .collect { values -> _response.value = cleanData(values) }
    }

    fun onRefresh(){
        fetchBiomarkersResponse()
    }

//    val biomarkersList = MutableLiveData<List<Biomarker>> ()
//    val isLoading = MutableLiveData(false)
//    val status = MutableLiveData(0)
//
//    fun getAllBiomarkers() {
//
//        isLoading.postValue(true)
//        val response = repository.getBiomarkers()
//        response.enqueue(object : Callback<List<Biomarker>> {
//            override fun onResponse(call: Call<List<Biomarker>>, response: Response<List<Biomarker>>) {
//                biomarkersList.postValue(cleanData(response.body()))
//                isLoading.postValue(false)
//            }
//
//            override fun onFailure(call: Call<List<Biomarker>>, t: Throwable) {
//                errorMessage.postValue(t.message)
//                isLoading.postValue(false)
//                biomarkersList.postValue(ArrayList())
//                status.postValue(2)
//            }
//        })
//    }
//
//    fun onRefresh(){
//        Log.d("TAG", "onRefresh: ")
//        getAllBiomarkers()
//    }
//

    private fun cleanData(response: NetworkResult<List<Biomarker>>?) : NetworkResult<List<Biomarker>> {
        if (response?.data != null) {
            response.data = response.data!!.filter { biomarker -> biomarker.symbol.isNotEmpty() }
            return response
        }
        return response!!
    }

}