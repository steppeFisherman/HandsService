package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface Repository {
    fun fetchData(): Flow<List<Item>>
}

class Base : Repository {

    private val list = emptyList<Item>().toMutableList()

//    override fun fetchData(): List<Item> {
//        list.add(Item("first", "first desc"))
//        return list
//    }

    override fun fetchData(): Flow<List<Item>> {
        list.add(Item("first", "first desc"))
        return flow { emit(list) }
    }
}