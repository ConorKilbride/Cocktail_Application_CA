package com.example.caapplication.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.caapplication.data.FavouriteEntity

@Dao
interface FavouriteDao {

    // this insert deals with creating by inserting and also updates by replacing them
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(favourite: FavouriteEntity)

    // favourites is defined in FavouriteEntity as the table name
    @Query("SELECT * FROM favourites WHERE id = :id")
    // Use ? as the object may be null - i.e. no entry in the DB for that id.
    fun getFavouriteById(id: Int): FavouriteEntity?
}