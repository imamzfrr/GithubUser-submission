package zfr.mobile.githubsubmission.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import zfr.mobile.githubsubmission.data.User
import zfr.mobile.githubsubmission.retrofit.ApiConfig

class FollowingViewModel : ViewModel() {
    private val listFollowing = MutableLiveData<ArrayList<User>>()

    fun setFollowing(username: String) {
        ApiConfig.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if(response.isSuccessful()){
                        listFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun  getFollowing(): LiveData<ArrayList<User>> {
        return listFollowing
    }
}
