package com.example.numbers.presentation.number

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numbers.data.Repository
import com.example.numbers.data.cache.NumbersDao
import com.example.numbers.data.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NumbersViewModel : ViewModel() {

    fun getAllNumbers(dao: NumbersDao, onResult: (Result.AllFacts) -> Unit) {
        val repository = Repository.RepositoryImpl(dao)
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                onResult.invoke(repository.getAllNumbers())
            }
        }
    }

}