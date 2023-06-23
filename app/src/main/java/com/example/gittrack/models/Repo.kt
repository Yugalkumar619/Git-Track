package com.example.gittrack.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class Repo(

    @PrimaryKey(autoGenerate = true)
    val repoId: Int,


    val name: String,
    val description: String,
    val html_url: String
    )
