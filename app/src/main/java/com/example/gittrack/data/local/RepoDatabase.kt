package com.example.gittrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gittrack.models.Repo

@Database(entities = [Repo::class], version = 1)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao() : RepoDao

}