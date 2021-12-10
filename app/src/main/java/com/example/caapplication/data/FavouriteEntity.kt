package com.example.caapplication.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favourites")
class FavouriteEntity(
    @PrimaryKey val id : Int,
    var myNotes : String
)