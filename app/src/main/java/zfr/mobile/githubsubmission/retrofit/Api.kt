package zfr.mobile.githubsubmission.retrofit

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import zfr.mobile.githubsubmission.data.User
import zfr.mobile.githubsubmission.data.UserDetailResponse
import zfr.mobile.githubsubmission.data.UserResponse
import zfr.mobile.githubsubmission.BuildConfig



interface Api {
    @GET("search/users")
    @Headers("Authorization: token " + BuildConfig.tokenkey)
    fun getSearch(
        @Query("q")query: String
    ): retrofit2.Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token " + BuildConfig.tokenkey)
    fun  getUserDetail(
        @Path("username") username: String
    ): retrofit2.Call<UserDetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token " + BuildConfig.tokenkey)
    fun  getFollowers(
        @Path("username") username: String
    ): retrofit2.Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token " + BuildConfig.tokenkey)
    fun  getFollowing(
        @Path("username") username: String
    ): retrofit2.Call<ArrayList<User>>
}
