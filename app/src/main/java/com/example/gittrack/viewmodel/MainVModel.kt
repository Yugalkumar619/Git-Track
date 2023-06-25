package com.example.gittrack.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gittrack.MainActivity
import com.example.gittrack.data.repository.RepoRepository
import com.example.gittrack.models.Repo
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVModel @Inject constructor(val repository: RepoRepository): ViewModel(){


    // Here are the variables used for creating custom URL
     var ownerName: String = ""
     var repoName: String = ""
     var url: String = "/repos/"

    private val repoLiveData = MutableLiveData<Repo>()
    private val shareRepoLiveData = MutableLiveData<Repo>()

    val item: MutableLiveData<Repo>
        get() = repoLiveData

    val shareItem: MutableLiveData<Repo>
        get() = shareRepoLiveData


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLocalRepo()

        }
    }


    // function to call api to get different repository
    fun buttonClick(owner: MutableState<TextFieldValue>, repo: MutableState<TextFieldValue>){

        ownerName = owner.value.text.trim()
        repoName = repo.value.text.trim()
        url = "/repos/$ownerName/$repoName"
        Log.d("YUGALKUMAR", url)

        viewModelScope.launch(Dispatchers.IO) {
            repository.getRepo(url)
        }
    }

    // function to redirect the user to github page of the repository
    fun itemClicked(item: Repo){
        this.item.postValue(item)
    }

    // function to share the repository to other apps
    fun shareClicked(shareItem: Repo){
        this.shareItem.postValue(shareItem)
    }

    val repo : LiveData<Repo>
        get() = repository.repo

    val lRepo : LiveData<List<Repo>>
        get() = repository.lRepo
}