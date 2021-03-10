package com.target.targetcasestudy.data

data class DealItem(
  var id: Int,
  var title: String,
  var description: String,
  var price: String,
  var aisle: String,
  var image_url: String,
  var regular_price: RegularPrice?,
  var sale_price: RegularPrice?
)