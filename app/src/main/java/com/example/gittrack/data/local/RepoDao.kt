package com.example.gittrack.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gittrack.models.Repo

@Dao
interface RepoDao {

    @Insert
    suspend fun addRepo(repo: Repo)

    @Query("SELECT * FROM repository")
    suspend fun getRepo(): List<Repo>
}