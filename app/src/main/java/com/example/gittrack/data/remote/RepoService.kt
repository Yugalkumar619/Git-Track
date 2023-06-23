package com.example.gittrack.data.remote

import com.example.gittrack.models.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface RepoService{

//    @GET("")
//    suspend fun getRepo() : Response<Repo>

//    @GET("/repos/martijn00/PhotoView")
//    suspend fun fetchRepo(): Repo
//
//    @GET("{id}")
//    suspend fun getRepo(@Path("id") groupId: String): Call<Repo?>?

    @GET
    suspend fun getUsers(@Url url: String?): Repo


//    @GET("/repos/martijn00/PhotoView")
//    suspend fun fetchRepo(): Repo


}