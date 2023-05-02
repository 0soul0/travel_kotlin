package com.sideproject.travel.model

data class Data(
    val id: Int,
    val images: List<Image>,
    val address: String,
    val email: String,
    val url: String,
    val tel: String,
    val introduction: String,
    val name: String,
    val modified: String
)