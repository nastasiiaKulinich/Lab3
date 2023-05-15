package com.example.numbers.data

import com.example.numbers.data.cache.NumbersDao
import com.example.numbers.data.cache.model.NumberCache
import com.example.numbers.data.cloud.RetrofitInstance
import com.example.numbers.data.model.Result
import java.net.UnknownHostException

interface Repository {

    suspend fun getFact(number: String): Result

    suspend fun getRandomFact(): Result

    suspend fun getAllNumbers(): Result.AllFacts

    class RepositoryImpl(private val dao: NumbersDao) : Repository {

        private val retrofit = RetrofitInstance.retrofit

        override suspend fun getFact(number: String): Result {
            return try {
                val result = retrofit.fact(number).get("text").asString
                dao.insert(NumberCache(number, result, System.currentTimeMillis()))
                Result.NumberFact(number, result)
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    Result.Error("No Internet connection!")
                else
                    Result.Error(e.message.toString())
            }
        }

        override suspend fun getRandomFact(): Result {
            return try {
                Result.NumberRandomFact(retrofit.random().get("text").asString)
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    Result.Error("No Internet connection!")
                else
                    Result.Error(e.message.toString())
            }
        }

        override suspend fun getAllNumbers(): Result.AllFacts {
            return Result.AllFacts(dao.allNumbers().map { Result.NumberFact(it.number, it.fact) })
        }
    }

}