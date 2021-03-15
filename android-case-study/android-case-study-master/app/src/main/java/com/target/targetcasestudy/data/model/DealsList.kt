package com.target.targetcasestudy.data.model


import com.google.gson.annotations.SerializedName

data class DealsList(
    @SerializedName("products")
    val products: List<DealItem>
)