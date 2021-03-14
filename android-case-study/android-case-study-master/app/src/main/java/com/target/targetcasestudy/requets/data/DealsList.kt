package com.target.targetcasestudy.requets.data


import com.google.gson.annotations.SerializedName

data class DealsList(
    @SerializedName("products")
    val products: List<DealItem>
)