package com.example.caapplication

import com.example.caapplication.data.CocktailEntity
import kotlinx.coroutines.launch
import android.util.Log
import androidx.lifecycle.*
import com.example.caapplication.dataaccess.RetrofitInstance

class MainViewModel : ViewModel() {

    // MutableLiveData - means this list can be changed at runtime
    private val _cocktails: MutableLiveData<List<CocktailEntity>> = MutableLiveData()

    // Cocktails is exposed to the UI - Fragment
    val cocktails: LiveData<List<CocktailEntity>>
        get() = _cocktails

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    // No Longer get the data from SampleDataProvider
    init {
        // here we get the cocktail list data to share with the user interface
        getCocktails()
    }

    private fun getCocktails() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedCocktails = RetrofitInstance.api.getCocktails()
            Log.i(TAG, "List of Cocktails : $fetchedCocktails")
            _cocktails.value = fetchedCocktails.drinks
            _isLoading.value = false
        }
    }
}