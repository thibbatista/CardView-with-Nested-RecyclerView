package com.thiagosantos.cardcomrecyclerview.repositories

import com.thiagosantos.cardcomrecyclerview.rest.RetrofitService


class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllItems() = retrofitService.getAllItems()

}