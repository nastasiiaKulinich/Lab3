package com.example.numbers.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbers.data.Repository
import com.example.numbers.data.cache.NumbersDao
import com.example.numbers.data.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel : ViewModel() {


    fun getNumber(dao: NumbersDao, number: String, onResult: (Result) -> Unit) {
        val repository = Repository.RepositoryImpl(dao)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFact(number)
            withContext(Dispatchers.Main) {
                onResult.invoke(result)
            }
        }
    }

    fun getRandomNumber(dao: NumbersDao, onResult: (Result) -> Unit) {
        val repository = Repository.RepositoryImpl(dao)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRandomFact()
            withContext(Dispatchers.Main) {
                onResult.invoke(result)
            }
        }
    }
}