package id.co.arya.ajaibassessment.datasource.repository

import id.co.arya.ajaibassessment.network.RetrofitService

class MainRepository(private val url: String) {
    suspend fun getRepoFromUser(username: String) =
        RetrofitService(url).RETROFIT_INSTANCE.getRepoFromUser(username)
}