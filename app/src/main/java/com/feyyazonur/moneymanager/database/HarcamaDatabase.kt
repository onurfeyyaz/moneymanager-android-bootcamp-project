package com.feyyazonur.moneymanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Harcama::class], version = 1, exportSchema = false)
abstract class HarcamaDatabase: RoomDatabase() {
    abstract val harcamaDatabaseDao: HarcamaDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: HarcamaDatabase? = null

        fun getInstance(context: Context): HarcamaDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HarcamaDatabase::class.java,
                        "harcama_gecmisi_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }


    }
}