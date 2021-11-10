package id.co.arya.ajaibassessment.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users/{user}/repos")
    suspend fun getRepoFromUser(@Path("user") user: String): ResponseBody

}