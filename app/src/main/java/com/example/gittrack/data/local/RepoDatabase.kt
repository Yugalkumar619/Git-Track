package com.example.gittrack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gittrack.models.Repo

@Database(entities = [Repo::class], version = 1)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao() : RepoDao

//    companion object{
//        @Volatile
//        private var INSTANCE: RepoDatabase? = null
//
//        fun getDatabase(context: Context): RepoDatabase{
//            if (INSTANCE == null){
//                synchronized(this){
//                    INSTANCE = Room.databaseBuilder(context,
//                    RepoDatabase::class.java,
//                        "repoDB")
//                        .build()
//                }
//            }
//            return INSTANCE!!
//        }
//    }
}