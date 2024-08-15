package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface Repository {
    suspend fun fetchData(list: MutableList<Item>): Flow<List<Item>>
}

class Base : Repository {
    override suspend fun fetchData(list: MutableList<Item>): Flow<List<Item>> {

        list.add(Item("Мёртвая", "first desc"))
        list.add(Item("Живая", "first desc"))
        list.add(Item("Жизнь", "first desc"))
        return flow<MutableList<Item>> { emit(list) }
    }
}