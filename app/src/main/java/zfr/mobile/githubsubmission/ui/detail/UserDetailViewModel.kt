package zfr.mobile.githubsubmission.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zfr.mobile.githubsubmission.data.UserDetailResponse
import zfr.mobile.githubsubmission.retrofit.ApiConfig

class UserDetailViewModel<T> : ViewModel() {
    private val user = MutableLiveData<UserDetailResponse>()

    fun setDetailUser(username: String) {
        ApiConfig.apiInstance.getUserDetail(username).enqueue(object : Callback<UserDetailResponse> {
            override fun onResponse(call: Call<UserDetailResponse>, response: Response<UserDetailResponse>) {
                if (response.isSuccessful) {
                    user.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                Log.d("Fail", t.message ?: "Unknown error")
            }
        })
    }

    fun getDetailUser(): LiveData<UserDetailResponse> {
        return user
    }
}
