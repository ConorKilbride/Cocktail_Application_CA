package com.example.caapplication.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.caapplication.data.FavouriteEntity

@Database(entities = [FavouriteEntity::class], version = 2, exportSchema = false)

abstract class AppDatabase: RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDao?

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "favourites.db"
                    )//.fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}