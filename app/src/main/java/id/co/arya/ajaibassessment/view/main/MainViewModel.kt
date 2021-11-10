package id.co.arya.ajaibassessment.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import id.co.arya.ajaibassessment.datasource.repository.MainRepository
import id.co.arya.ajaibassessment.utils.ResourceApiModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getRepoFromUser(username: String) = liveData(Dispatchers.IO) {
        emit(ResourceApiModel.loading(data = null))
        try {
            emit(ResourceApiModel.success(data = mainRepository.getRepoFromUser(username)))
        } catch (e: Exception) {
            emit(ResourceApiModel.error(data = null, message = e.localizedMessage ?: "Something Went Wrong"))

        }
    }

}