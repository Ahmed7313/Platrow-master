package com.example.platrow.model


data class Product(
    val id: Int,
    val listImageUrl: List <String>,
    val name: String,
    val wieght: String,
    val price: Double,
    val calories: String,
    val type: String)