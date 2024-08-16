package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Repository {

    suspend fun fetchData(list: MutableList<Item>): Flow<List<Item>>

}

class Base : Repository {

    private val deadLifeTerms = DeadLifeTerms.ThreeByThree()

    override suspend fun fetchData(list: MutableList<Item>): Flow<List<Item>> {

        val listFiltered = deadLifeTerms.filter(list).toMutableList()

        return flow<MutableList<Item>> { emit(listFiltered) }
    }
}