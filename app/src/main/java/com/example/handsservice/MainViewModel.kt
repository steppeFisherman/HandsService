package com.example.handsservice

import androidx.lifecycle.ViewModel
import com.example.data.Base
import com.example.data.Item
import com.example.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val repository: Repository = Base()
    private var _state = MutableStateFlow<List<Item>>(mutableListOf())

    val state: StateFlow<List<Item>>
        get() = _state.asStateFlow()

//    fun createItem(): StateFlow<List<Item>> {
//        _state.update { repository.fetchData() }
//        return state
//    }

    suspend fun createItem(): StateFlow<List<Item>> {
        val fetchData = repository.fetchData().firstOrNull()
        _state.update { checkNotNull(fetchData) }
        return state
    }
}
