package com.example.gittrack.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.gittrack.data.remote.RepoService
import com.example.gittrack.data.local.RepoDao
import com.example.gittrack.models.Repo
import retrofit2.Call
import javax.inject.Inject


class RepoRepository @Inject constructor(private val repoService: RepoService, private val repoDao: RepoDao){

    private val repoLiveData = MutableLiveData<Repo>()
    private val lRepoLiveData = MutableLiveData<List<Repo>>()

    val repo: MutableLiveData<Repo>
    get() = repoLiveData

    val lRepo: MutableLiveData<List<Repo>>
    get() = lRepoLiveData


    // Demo data if we are not able to get the response
    val repodemo: Repo = Repo(1,"My name is Yugal","Living Legend","www.yugal-kumar.com")

    suspend fun getRepo(owner: String){
        var result: Repo

        try {

           result = repoService.getUsers(owner)
            addRepo(result)
            getLocalRepo()

        }catch (e: Exception){
            Log.e("Network Error",e.message.toString())
            result = repodemo
            getLocalRepo()
        }

        repoLiveData.postValue(result)
    }

    suspend  fun getLocalRepo(){
        lRepo.postValue(repoDao.getRepo())
    }

    suspend fun addRepo(repo: Repo) = repoDao.addRepo(repo)
}



private fun <T> MutableLiveData<T>.postValue(result: Call<T?>?) {

}
