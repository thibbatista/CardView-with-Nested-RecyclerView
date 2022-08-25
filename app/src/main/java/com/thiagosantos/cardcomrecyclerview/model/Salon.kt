package com.thiagosantos.cardcomrecyclerview.model

data class Salon(

    var categoryTitle: String,
    var address: String,
    var image: String,
    var note: Float,
    var evaluation: Int,
    var categoryItemList: List<ImageItem>

)

