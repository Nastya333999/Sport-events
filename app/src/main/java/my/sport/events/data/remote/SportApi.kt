package my.sport.events.data.remote

import retrofit2.http.GET

const val API_V1 = "v1"
const val BASE_URL = "https://62a054d8202ceef7086aed64.mockapi.io/api/"

interface SportApi {
    @GET("$API_V1/demo")
    suspend fun getAll(): List<SportDTO>
}