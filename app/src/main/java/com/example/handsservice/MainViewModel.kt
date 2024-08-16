package com.example.handsservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Base
import com.example.data.Item
import com.example.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository: Repository = Base()
    private var mList = mutableListOf<Item>()
    private var _state = MutableStateFlow<List<Item>>(emptyList())
    val state get() = _state.asStateFlow()

    fun createItem() {
        viewModelScope.launch {
            repository.fetchData(mList).collect { list ->
                mList = list.toMutableList()
                _state.emit(list)
            }
        }
    }
}
