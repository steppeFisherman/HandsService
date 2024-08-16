package com.example.handsservice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.handsservice.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val adapter = MainAdapter(this)
        val adapter = SimpleAdapter(this)
        binding.rvMain.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { list ->
                    adapter.submitList(list)
                }
            }
        }

        binding.btnCreate.setOnClickListener {
            lifecycleScope.launch {
                vm.createItem()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvMain.adapter = null
    }
}