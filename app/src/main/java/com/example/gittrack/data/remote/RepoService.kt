package com.example.gittrack.data.remote

import com.example.gittrack.models.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface RepoService{

// Service class to call the API using dynamic URL
    @GET
    suspend fun getUsers(@Url url: String?): Repo

}