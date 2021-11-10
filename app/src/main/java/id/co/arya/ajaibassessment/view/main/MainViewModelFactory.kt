package id.co.arya.ajaibassessment.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.arya.ajaibassessment.datasource.repository.MainRepository

class MainViewModelFactory(private val url: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(MainRepository(url)) as T
    }
}