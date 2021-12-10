package com.example.caapplication.data

import android.os.Parcel
import com.example.caapplication.NEW_COCKTAIL_ID
import java.util.*
import android.os.Parcelable

class CocktailEntity (
    val idDrink: Int,
    val strDrink: String?,
    val strCategory: String?,
    val strInstructions: String?,
    val strDrinkThumb: String?
) : Parcelable
{
    // Glide uses this get method to get the image from the web using the URL
//    val thumbnailImageUrl
//        get() = strImageSource

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){
    }

    // no arguments constructor - if no values are passed in this one is executed.
        //    constructor() : this(NEW_COCKTAIL_ID,"","","")
    // New Cocktail - this constructor is called when there is a name, category and instructions, but no cocktail ID yet
        //      constructor(strDrink: String, strCategory: String, strInstructions: String) : this(NEW_COCKTAIL_ID, strDrink, strCategory, strInstructions)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idDrink)
        parcel.writeString(strDrink)
        parcel.writeString(strCategory)
        parcel.writeString(strInstructions)
        parcel.writeString(strDrinkThumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CocktailEntity> {
        override fun createFromParcel(parcel: Parcel): CocktailEntity {
            return CocktailEntity(parcel)
        }

        override fun newArray(size: Int): Array<CocktailEntity?> {
            return arrayOfNulls(size)
        }
    }

}