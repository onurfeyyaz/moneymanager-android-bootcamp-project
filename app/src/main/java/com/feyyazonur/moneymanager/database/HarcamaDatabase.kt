package com.feyyazonur.moneymanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.feyyazonur.moneymanager.model.Harcama

@Database(entities = [Harcama::class], version = 2, exportSchema = false)
abstract class HarcamaDatabase: RoomDatabase() {

    abstract fun harcamaDatabaseDao(): HarcamaDatabaseDao

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