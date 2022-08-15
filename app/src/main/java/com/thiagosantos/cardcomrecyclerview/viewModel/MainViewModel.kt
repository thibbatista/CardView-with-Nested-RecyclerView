package com.thiagosantos.cardcomrecyclerview.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thiagosantos.cardcomrecyclerview.model.Salon
import com.thiagosantos.cardcomrecyclerview.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val allCategoryList = MutableLiveData<List<Salon>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCategory() {

        val response = repository.getAllItems()
        response.enqueue(object : Callback<List<Salon>> {
            override fun onResponse(call: Call<List<Salon>>, response: Response<List<Salon>>) {
                allCategoryList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Salon>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}