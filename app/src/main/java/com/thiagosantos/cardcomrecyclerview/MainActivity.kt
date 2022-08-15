package com.thiagosantos.cardcomrecyclerview

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thiagosantos.cardcomrecyclerview.adapter.RvVerticalAdapter
import com.thiagosantos.cardcomrecyclerview.databinding.ActivityMainBinding
import com.thiagosantos.cardcomrecyclerview.repositories.MainRepository
import com.thiagosantos.cardcomrecyclerview.rest.RetrofitService
import com.thiagosantos.cardcomrecyclerview.viewModel.MainViewModel
import com.thiagosantos.cardcomrecyclerview.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var rvVerticalAdapter: RvVerticalAdapter
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
    }

    override fun onStart() {
        super.onStart()

        viewModel.allCategoryList.observe(this) {
            Log.d(ContentValues.TAG, "onCreate: $it")

            rvVerticalAdapter = RvVerticalAdapter(this, it)
            binding.rvVertical.layoutManager = LinearLayoutManager(this)
            binding.rvVertical.adapter = rvVerticalAdapter

        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllCategory()

    }
}