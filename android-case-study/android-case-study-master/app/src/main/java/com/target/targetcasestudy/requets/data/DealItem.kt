package com.target.targetcasestudy.requets.data


import com.google.gson.annotations.SerializedName

data class DealItem(
    @SerializedName("aisle")
    val aisle: String, // b2
    @SerializedName("description")
    val description: String, // minim ad et minim ipsum duis irure pariatur deserunt eu cillum anim ipsum velit tempor eu pariatur sunt mollit tempor ut tempor exercitation occaecat ad et veniam et excepteur velit esse eu et ut ipsum consectetur aliquip do quis voluptate cupidatat eu ut consequat adipisicing occaecat adipisicing proident laborum laboris deserunt in laborum est anim ad non
    @SerializedName("id")
    val id: Int, // 0
    @SerializedName("image_url")
    val imageUrl: String, // https://picsum.photos/id/0/300/300
    @SerializedName("regular_price")
    val regularPrice: RegularPrice,
    @SerializedName("sale_price")
    val salePrice: SalePrice,
    @SerializedName("title")
    val title: String // non mollit veniam ex
)