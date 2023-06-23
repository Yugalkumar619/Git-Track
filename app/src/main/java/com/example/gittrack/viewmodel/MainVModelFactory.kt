package com.example.gittrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gittrack.data.repository.RepoRepository

class MainVModelFactory(private val repository: RepoRepository): ViewModelProvider.Factory{

   override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return MainVModel(repository) as T
    }
}