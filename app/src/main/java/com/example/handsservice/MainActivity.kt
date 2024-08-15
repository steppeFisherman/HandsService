package com.example.handsservice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.handsservice.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val vm by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        lifecycleScope.launch {
            vm.state.collect { state ->
                state
                val result = state
                result
            }
        }

        binding.btnCreate.setOnClickListener {
            lifecycleScope.launch {
                vm.createItem()
            }
        }
    }
}