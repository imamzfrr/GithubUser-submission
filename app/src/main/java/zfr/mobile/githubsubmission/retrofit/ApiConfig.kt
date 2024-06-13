package zfr.mobile.githubsubmission.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import zfr.mobile.githubsubmission.BuildConfig

object ApiConfig {
    private const val BASE_URL = BuildConfig.apiURL

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance = retrofit.create(Api::class.java)
}
