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
    val repodemo: Repo = Repo(1,"Please fill correct information","Living Legend","www.yugal-kumar.com")


    // function to get the repository from API
    suspend fun getRepo(owner: String){
        var result: Repo

        try {
           result = repoService.getUsers(owner)
            val lR = repoDao.getRepo()

            if (lR.contains(result)){
                repo.postValue(Repo(0,"Repository already exist","",""))
            }else{
                addRepo(result)
                getLocalRepo()
                repoLiveData.postValue(result)
            }
        }catch (e: Exception){
            Log.e("Network Error",e.message.toString())
            result = repodemo
            getLocalRepo()
            repoLiveData.postValue(result)
        }


    }


    // Function to get the repository data from local database
    suspend  fun getLocalRepo(){
        lRepo.postValue(repoDao.getRepo())
    }


    // Function to add the repository to the local database
    suspend fun addRepo(repo: Repo) = repoDao.addRepo(repo)
}



private fun <T> MutableLiveData<T>.postValue(result: Call<T?>?) {

}
