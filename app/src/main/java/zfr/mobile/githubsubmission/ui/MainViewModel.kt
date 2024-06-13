package zfr.mobile.githubsubmission.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import zfr.mobile.githubsubmission.data.User
import zfr.mobile.githubsubmission.data.UserResponse
import zfr.mobile.githubsubmission.retrofit.ApiConfig

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String){
        ApiConfig.apiInstance
            .getSearch(query)
            .enqueue(object : retrofit2.Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Fail", it) }
                }
            })
    }

    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUsers
    }

}
