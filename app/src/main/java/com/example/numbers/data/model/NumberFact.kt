package com.example.numbers.data.model

sealed class Result {

    data class NumberRandomFact(val fact: String) : Result()

    data class NumberFact(val number: String, val fact: String) : Result()

    data class AllFacts(val numberFacts: List<NumberFact>) : Result()

    data class Error(val message: String) : Result()
}